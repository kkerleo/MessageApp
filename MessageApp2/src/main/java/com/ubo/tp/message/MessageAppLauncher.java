package main.java.com.ubo.tp.message;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.DatabaseObserver;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.ihm.MessageApp;
import main.java.com.ubo.tp.message.ihm.MessageAppMainView;
import mock.MessageAppMock;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncher {

	/**
	 * Indique si le mode bouchoné est activé.
	 */
	protected static boolean IS_MOCK_ENABLED = true;

	/**
	 * Launcher.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		IDatabaseObserver databaseObserver = new DatabaseObserver();
		IDatabase database = new Database();
		EntityManager entityManager = new EntityManager(database);
		database.addObserver(databaseObserver);
		MessageAppMainView messageAppMainView = new MessageAppMainView();

//		if (IS_MOCK_ENABLED) {
//			MessageAppMock mock = new MessageAppMock(database, entityManager);
//			mock.showGUI();
//		}

		MessageApp messageApp = new MessageApp(database, entityManager, messageAppMainView);
		messageApp.init();
		messageApp.show();
	}
}
