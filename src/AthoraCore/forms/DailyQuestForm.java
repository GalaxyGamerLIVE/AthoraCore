package AthoraCore.forms;

import AthoraCore.api.AthoraPlayer;
import AthoraCore.components.Quest;
import AthoraCore.util.Helper;
import AthoraCore.util.Vars;
import AthoraCore.util.manager.DailyQuestManager;
import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import ru.nukkitx.forms.elements.SimpleForm;

public class DailyQuestForm {
    public DailyQuestForm(Player player) {
        SimpleForm form = (new SimpleForm("Explorer Finn - Daily Quest")).setContent("§fHey! ich hab hier eine Liste an Sachen, kannst\ndu mir die Besorgen?\n\n§r§7§o(Täglicher Reset um 2:00 Uhr)");
        Quest[] allQuests = DailyQuestManager.getDailyQuests();
        Quest[] completedQuests = DailyQuestManager.getCompletedQuests(player);
        Quest[] notCompletedQuests = new Quest[]{};
        for (Quest allQuest : allQuests) {
            boolean completed = false;
            for (Quest completedQuest : completedQuests) {
                if (allQuest.getQuestID() == completedQuest.getQuestID()) {
                    completed = true;
                }
            }
            if (!completed) {
                notCompletedQuests = Helper.append(notCompletedQuests, allQuest);
            }
        }
        for (Quest quest : notCompletedQuests) {
            form.addButton(TextFormat.colorize('&', quest.getQuestType().getName()) + "\n§r§o§c§lAb Level " + quest.getQuestType().getRequiredLevel());
        }
        Quest[] finalNotCompletedQuests = notCompletedQuests;
        form.send(player, (targetPlayer, targetForm, data) -> {
            if (data == -1) return;
            Quest selectedQuest = finalNotCompletedQuests[data];
            if (selectedQuest.getQuestType().getRequiredLevel() > AthoraPlayer.getLevel(targetPlayer)) {
                player.sendMessage(Vars.PREFIX + TextFormat.RED + "Du erfüllst nicht die Level Anforderung!");
                return;
            }
            new DailyQuestDetailForm(targetPlayer, selectedQuest);
        });
    }
}
