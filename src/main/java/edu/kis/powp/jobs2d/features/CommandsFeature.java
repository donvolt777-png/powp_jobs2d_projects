package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.gui.CommandPreviewWindow;
import edu.kis.powp.jobs2d.command.gui.CommandPreviewWindowObserver;
import edu.kis.powp.jobs2d.command.gui.SelectImportCommandOptionListener;
import edu.kis.powp.jobs2d.command.importer.JsonCommandImportParser;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentFlippedCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentRotatedCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentScaledDownCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentScaledUpCommandOptionListener;

public class CommandsFeature {

    private static CommandManager commandManager;

    public static void setupCommandManager() {
        commandManager = new CommandManager();

        LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
        commandManager.getChangePublisher().addSubscriber(loggerObserver);
    }

    /**
     * Set up the "Commands" menu in the application GUI.
     */
    public static void setupCommandsMenu(Application application) {
        application.addComponentMenu(CommandsFeature.class, "Commands");

        application.addComponentMenuElement(CommandsFeature.class, "Run command",
            new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

        application.addComponentMenuElement(CommandsFeature.class, "Flip",
            new SelectRunCurrentFlippedCommandOptionListener());

        application.addComponentMenuElement(CommandsFeature.class, "Rotate 90",
            new SelectRunCurrentRotatedCommandOptionListener());

        application.addComponentMenuElement(CommandsFeature.class, "Scale 2.0",
            new SelectRunCurrentScaledUpCommandOptionListener());

        application.addComponentMenuElement(CommandsFeature.class, "Scale 0.5",
            new SelectRunCurrentScaledDownCommandOptionListener());
    }

    public static void setupWindows(Application application) {

        CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
        SelectImportCommandOptionListener importListener = new SelectImportCommandOptionListener(
            CommandsFeature.getDriverCommandManager(),
            new JsonCommandImportParser()
        );
        commandManager.setImportActionListener(importListener);
        application.addWindowComponent("Command Manager", commandManager);

        CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
            commandManager);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);

        CommandPreviewWindow commandPreviewWindow = new CommandPreviewWindow();
        commandManager.setPreviewWindow(commandPreviewWindow);
        application.addWindowComponent("Command Preview", commandPreviewWindow);
        CommandPreviewWindowObserver previewObserver = new CommandPreviewWindowObserver(
            commandPreviewWindow,
            CommandsFeature.getDriverCommandManager()
        );
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(previewObserver);
    }

    /**
     * Get manager of application driver command.
     * 
     * @return plotterCommandManager.
     */
    public static CommandManager getDriverCommandManager() {
        return commandManager;
    }
}
