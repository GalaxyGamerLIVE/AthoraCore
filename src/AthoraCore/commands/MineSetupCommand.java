package AthoraCore.commands;

import AthoraCore.main.Main;
import AthoraCore.util.manager.MineSetupManager;
import AthoraCore.util.Vars;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.utils.TextFormat;

public class MineSetupCommand extends PluginCommand<Main> {

    public MineSetupCommand(Main owner) {
        super("minesetup", owner);
        this.setDescription("Bearbeite die Erzeverteilung in der Mine!");
        this.setPermission("athora.mine.setup.command");
        this.setPermissionMessage(Vars.PREFIX + "Du hast keine Berechtigung diesen Befehl auszuführen!");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission("athora.mine.setup.command")) {
            sender.sendMessage(Vars.PREFIX + "Du hast keine Berechtigung diesen Befehl auszuführen!");
        }
        Player player = (Player) sender;
        boolean setupModeEnabled = MineSetupManager.toggleSetupMode(player);
        if (setupModeEnabled) {
            player.sendMessage(Vars.PREFIX + TextFormat.GREEN + "Der Mine Setup Modus wurde erfolgreich aktiviert!");
        } else {
            player.sendMessage(Vars.PREFIX + TextFormat.RED + "Der Mine Setup Modus wurde erfolgreich deaktiviert!");
        }
        return true;
    }
}
