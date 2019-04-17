package me.markiscool.armorstandarms;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorStandArmsPlugin extends JavaPlugin {

    private Permission permission;

    @Override
    public void onEnable() {
        permission = new Permission("armorstandarms.allow");
        permission.setDefault(PermissionDefault.OP);
        getServer().getPluginManager().registerEvents(new ArmorStandListener(this), this);
        new MetricsLite(this);
    }

    public Permission getPermission() {
        return permission;
    }

}
