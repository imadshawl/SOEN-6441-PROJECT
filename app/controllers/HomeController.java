package controllers;

import javax.inject.Inject;
import components.OwnerDetailsComponent;
import components.SkillsComponent;
import components.WordStatComponent;
import play.data.DynamicForm;
import play.mvc.*;
import play.routing.*;
import components.SearchComponent;
import java.util.concurrent.CompletionStage;

/**
 * This is the main and only controller.
 * It takes requests from all the path and returns the appropriate result.
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private SearchComponent searchComponent;

    @Inject
    private OwnerDetailsComponent ownerDetailsComponent;

    @Inject
    private SkillsComponent skillsComponent;

    @Inject
    private play.data.FormFactory formFactory;

    @Inject
    private WordStatComponent wordStatComponent;

    public Result index() {
        return ok(views.html.index.render());
    }

    /**
     * Methods that handles get requests from /skills path.
     * The method calls the search component to fetch the result.
     * It returns skills.scala.html rendered with SkillsSearchResult model.
     * It contains a list of 10 latest projects for the skillId provided in the request.
     * The format of the rendered result is the same as the main results page.
     *
     * @param skillId - The job id for the skill as mentioned in Freelancer
     * @param skillName - Name of the skill or job.
     * @return Result wrapped in a CompletionStage.
     */
    public CompletionStage<Result> skills(String skillId, String skillName) {
        return skillsComponent.getSkillSearchResult(skillId, skillName)
                .thenApply(searchResults -> ok(views.html.skills.render(searchResults)));
    }

    /**
     * This method handles get requests from /search path.
     * The path is invoked when the user click on the search button.
     * This method takes the search term as an argument.
     * It calls the SearchComponent to get the result.
     * It returns <code>searchResult.scala.html</code> rendered with SearchResults.
     *
     * @param searchTerm - The search term to look for in the projects.
     * @return Result wrapped in a CompletionStage.
     */
    public CompletionStage<Result> search(String searchTerm) {
        return searchComponent.searchProjects(searchTerm)
                .thenApply(searchResults -> ok(views.html.searchResult.render(searchResults)));
    }

    /**
     * This method handles post requests from /wordStat path.
     * It receives the word stat in form data part of the request.
     * It returns wordStat.scala.html rendered with the map of words that was received in the form of a table.
     *
     * @param request - The form data section contains the word count map to be rendered
     * @return Result that contains the table representation of the word count map.
     */
    public Result wordStat(Http.Request request) {
        DynamicForm data = formFactory.form().bindFromRequest(request);
        return ok(views.html.wordStat.render(wordStatComponent.getMapFromString(data.get("wordCountMap"))));
    }

    /**
     * This method handles get requests from /ownerDetails path.
     * It receives ownerId as query params in the request.
     * It internally calls OwnerDetailsComponent for fetch the employer details and 10 latest projects.
     * It returns employerDetails.scala.html in the response rendered with OwnerDetailsResult.
     *
     * @param ownerId - Id of the employer
     * @return Result wrapped in a CompletionStage.
     */
    public CompletionStage<Result> ownerDetails(final String ownerId) {
        return ownerDetailsComponent.getOwnerDetailsResult(ownerId)
                .thenApplyAsync(result -> ok(views.html.employerDetails.render(result)));
    }

    /**
     * Returns routes that can be used in the js scripts of the project.
     * This is mostly used to make an ajax call from the main search page once the search button is clicked.
     *
     * @param request - Http request
     * @return Result that contains the JavaScriptReverseRouter
     */
    public Result javascriptRoutes(Http.Request request) {
        return ok(JavaScriptReverseRouter.create(
                "jsRoutes",
                "jQuery.ajax",
                request.host(),
                routes.javascript.HomeController.search()))
                .as(Http.MimeTypes.JAVASCRIPT);
    }
}
