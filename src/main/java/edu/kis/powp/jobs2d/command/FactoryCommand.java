package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class FactoryCommand {
    public static DriverCommand createHouse() {
        int baseX = -50;
        int baseY = -50;
        int squareSize = 100;
        int roofHeight = 60;

        List<DriverCommand> squareCommands = new ArrayList<>();
        squareCommands.add(new SetPositionCommand(baseX, baseY));
        squareCommands.add(new OperateToCommand(baseX + squareSize, baseY));
        squareCommands.add(new OperateToCommand(baseX + squareSize, baseY + squareSize));
        squareCommands.add(new OperateToCommand(baseX, baseY + squareSize));
        squareCommands.add(new OperateToCommand(baseX, baseY));

        CompoundCommand square = CompoundCommand.builder().add(squareCommands).build();

        List<DriverCommand> roofCommands = new ArrayList<>();
        roofCommands.add(new SetPositionCommand(baseX, -(baseY + squareSize)));
        roofCommands.add(new OperateToCommand(baseX + squareSize / 2, -(baseY + squareSize + roofHeight)));
        roofCommands.add(new OperateToCommand(baseX + squareSize, -(baseY + squareSize)));

        return CompoundCommand.builder()
                .add(square)
                .add(roofCommands)
                .setName("House command")
                .build();
            
    }

    public static DriverCommand createSecretCommand(){
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(-20, -50));
        commands.add(new OperateToCommand(-20, -50));
        commands.add(new SetPositionCommand(-20, -40));
        commands.add(new OperateToCommand(-20, 50));
        commands.add(new SetPositionCommand(0, -50));
        commands.add(new OperateToCommand(0, -50));
        commands.add(new SetPositionCommand(0, -40));
        commands.add(new OperateToCommand(0, 50));
        commands.add(new SetPositionCommand(70, -50));
        commands.add(new OperateToCommand(20, -50));
        commands.add(new OperateToCommand(20, 0));
        commands.add(new OperateToCommand(70, 0));
        commands.add(new OperateToCommand(70, 50));
        commands.add(new OperateToCommand(20, 50));
        return CompoundCommand.builder()
                .add(commands)
                .setName("Top Secret Command")
                .build();
    }
}
