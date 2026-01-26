package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.gui.CommandPreviewWindow;
import edu.kis.powp.jobs2d.command.gui.CommandPreviewWindowObserver;
import edu.kis.powp.jobs2d.command.gui.SelectImportCommandOptionListener;
import edu.kis.powp.jobs2d.command.importer.JsonCommandImportParser;
import edu.kis.powp.jobs2d.drivers.AnimatedDriverDecorator;
import edu.kis.powp.jobs2d.drivers.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.RecordingDriverDecorator;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.UsageTrackingDriverDecorator;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.visitor.VisitableJob2dDriver;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.CanvasFeature;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MonitoringFeature;
import edu.kis.powp.jobs2d.features.ViewFeature;

import edu.kis.powp.jobs2d.drivers.transformation.DriverFeatureFactory;
import edu.kis.powp.jobs2d.canvas.CanvasFactory;


public class TestJobs2dApp {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Setup test concerning preset figures in context.
     *
     * @param application Application context.
     */
    private static void setupPresetTests(Application application) {
        SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
                DriverFeature.getDriverManager());
        SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
                DriverFeature.getDriverManager());
        SelectTestCompoundCommandOptionListener selectTestCompoundCommandOptionListener = new SelectTestCompoundCommandOptionListener(
                DriverFeature.getDriverManager());
        SelectCountCommandOptionListener selectCountCommandOptionListener = new SelectCountCommandOptionListener(CommandsFeature.getDriverCommandManager());
        SelectCountDriverOptionListener selectCountDriverOptionListener = new SelectCountDriverOptionListener();

        application.addTest("Figure Joe 1", selectTestFigureOptionListener);
        application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
        application.addTest("Figure House - CompoundCommand", selectTestCompoundCommandOptionListener);
        application.addTest("Count commands - Visitor", selectCountCommandOptionListener);
        application.addTest("Count drivers - Visitor", selectCountDriverOptionListener);
    }

    /**
     * Setup test using driver commands in context.
     *
     * @param application Application context.
     */
    private static void setupCommandTests(Application application) {
        ViewFeature.addMouseListenerToControlPanel(new CanvasMouseListener());
        application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application("Jobs 2D");

                // Setup application features
                ViewFeature.setupViewPlugin(app);
                DrawerFeature.setupDrawerPlugin(app);
                CanvasFeature.setupCanvasPlugin(app);
                CommandsFeature.setupCommandManager();
                CommandsFeature.setupCommandsMenu(app);
                CommandsFeature.setupWindows(app);
                MonitoringFeature.setupLoggerMenu(app);
                MonitoringFeature.setupMonitoringPlugin(app, logger);
                DriverFeature.setupDriverPlugin(app, logger);

                // Setup tests
                setupPresetTests(app);
                setupCommandTests(app);

                app.setVisibility(true);
            }
        });
    }

}
