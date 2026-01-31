package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.FactoryCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;

public class SelectTestCompoundCommandOptionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand house = FactoryCommand.createHouse();
        CommandsFeature.getDriverCommandManager().setCurrentCommand(house);
    }
}
