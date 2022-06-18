package controllers;

import actors.*;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import com.fasterxml.jackson.databind.JsonNode;
import components.SkillsComponent;
import models.Skill;
import play.libs.F;
import play.libs.streams.ActorFlow;
import play.mvc.*;
import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class WebSocketController extends Controller {
    private ActorSystem actorSystem;
    private Materializer materializer;
    private String skillId;
    private String skillName;
    @Inject
    private SkillsComponent skillsComponent;

    @Inject
    public WebSocketController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    public CompletionStage<Result> wsSkills(Http.Request request){
        final Map<String, String[]> queryString = request.queryString();

        final String url = routes.WebSocketController.skillsWebSocket().webSocketURL(request);
        skillId = queryString.get("skillId")[0];
        skillName = queryString.get("skillName")[0];
        return skillsComponent.getSkillSearchResult(queryString.get("skillId")[0], queryString.get("skillName")[0])
                .thenApply(searchResults -> ok(views.html.wsSkills.render(searchResults, url)));
    }

    public WebSocket skillsWebSocket() {
        return WebSocket.Json.acceptOrResult(this::createActorFlow);
    }

    private CompletionStage<F.Either<Result, Flow<JsonNode, JsonNode, ?>>> createActorFlow(
            Http.RequestHeader request) {
        return CompletableFuture
                .completedFuture(F.Either.Right(createFlowForActor(skillId, skillName)));
    }


    private Flow<JsonNode, JsonNode, ?> createFlowForActor(final String skillId, final String skillName) {
        return ActorFlow.actorRef(out -> SkillsActor.props(out, skillId, skillName), actorSystem, materializer);
    }

}
