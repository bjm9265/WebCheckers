package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  // CONSTANT KEYS TO KEEP TRACK OF VIEW ATTRIBUTES AND IMPORTANT INFORMATION
  public static final String MESSAGE_ATTR = "message";
  public static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  public static final String CURRENT_USER_KEY = "CURRENT_USER";
  public static final String CURRENT_USER_ATTR = "currentUser";
  public static final String PLAYER_LIST_ATTR = "playerList";
  public static final String TITLE_ATTR = "title";
  public static final String TITLE = "Welcome!";
  public static final String VIEW_NAME = "home.ftl";

  // WebServer provided information
  private final TemplateEngine templateEngine;
  private final PlayerLobby lobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param lobby
   *   the PlayerLobby which contains a list of all players
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final PlayerLobby lobby, final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.lobby = lobby;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    // retrieve the HTTP session
    final Session httpSession = request.session();
    // retrieve the player object
    final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER_KEY);

    // Create the view map to insert objects into
    Map<String, Object> vm = new HashMap<>();

    // update the title of the page
    vm.put(TITLE_ATTR, TITLE);
    // display a user message in the Home page
    vm.put(MESSAGE_ATTR, WELCOME_MSG);
    // update the currentPlayer attribute so the page can dynamically change accordingly
    vm.put(CURRENT_USER_ATTR, player);

    // If player is already logged in, display the current list of signed-in players
    if(player != null) {
      vm.put(PLAYER_LIST_ATTR, lobby.giveRoster(player));
    } else {
      vm.put(PLAYER_LIST_ATTR, lobby.lobbySize(player));
    }

    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
