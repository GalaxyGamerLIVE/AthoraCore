package AthoraCore.commands;

import AthoraCore.main.Main;
import AthoraCore.util.manager.ServerManager;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;

public class PlotsCommand extends PluginCommand<Main> {

    public PlotsCommand(Main owner) {
        super("plots", owner);
        this.setDescription("Teleportiert dich zum Plot Server!");
        this.commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.isPlayer()) {
            return false;
        }
        Player player = (Player) sender;
        ServerManager.sendPlayerToServer(player, ServerManager.PLOT_SERVER);
        return true;
    }
}
