package net.countercraft.movecraft.combat.features.tracking.events;

import net.countercraft.movecraft.combat.features.tracking.DamageRecord;
import net.countercraft.movecraft.combat.localisation.I18nSupport;
import net.countercraft.movecraft.combat.utils.NameUtils;
import net.countercraft.movecraft.craft.PlayerCraft;
import net.countercraft.movecraft.events.CraftEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;


public class CraftSunkByEvent extends CraftEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final List<DamageRecord> causes;

    public CraftSunkByEvent(@NotNull PlayerCraft craft, @NotNull List<DamageRecord> causes) {
        super(craft);
        this.causes = causes;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Nullable
    public List<DamageRecord> getCauses() {
        return this.causes;
    }

    @Nullable
    public DamageRecord getLastRecord() {
        return causes.get(causes.size() - 1);
    }

    @NotNull
    public String causesToString() {
        DamageRecord latestDamage = getLastRecord();
        HashSet<OfflinePlayer> players = new HashSet<>();
        for (DamageRecord r : this.causes) {
            players.add(r.getCause());
        }
        assert latestDamage != null;
        players.remove(latestDamage.getCause());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((PlayerCraft) this.craft).getPilot().getDisplayName());
        stringBuilder.append(" ").append(I18nSupport.getInternationalisedString("Killfeed - Sunk By")).append(" ");
        stringBuilder.append(NameUtils.offlineToName(latestDamage.getCause()));
        if (players.size() < 1)
            return stringBuilder.toString();

        stringBuilder.append(" ").append(I18nSupport.getInternationalisedString("Killfeed - With Assists")).append(" ");
        for (OfflinePlayer p : players) {
            stringBuilder.append(NameUtils.offlineToName(p));
            stringBuilder.append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}
