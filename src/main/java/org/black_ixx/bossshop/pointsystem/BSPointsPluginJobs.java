package org.black_ixx.bossshop.pointsystem;

import com.gamingmesh.jobs.Jobs;
import org.bukkit.OfflinePlayer;


public class BSPointsPluginJobs extends BSPointsPlugin {

    public BSPointsPluginJobs() {
        super("Jobs", "JobsReborn");
    }

    @Override
    public double getPoints(OfflinePlayer player) {
        return Jobs.getPlayerManager().getJobsPlayer(player.getName()).getPointsData().getCurrentPoints();
    }
    @Override
    public double setPoints(OfflinePlayer player, double points) {
        Jobs.getPlayerManager().getJobsPlayer(player.getName()).getPointsData().setPoints(points);
        return points;
    }

    @Override
    public double takePoints(OfflinePlayer player, double points) {
        Jobs.getPlayerManager().getJobsPlayer(player.getName()).getPointsData().takePoints(points);
        return getPoints(player);
    }

    @Override
    public double givePoints(OfflinePlayer player, double points) {
        Jobs.getPlayerManager().getJobsPlayer(player.getName()).getPointsData().addPoints(points);
        return getPoints(player);
    }

    @Override
    public boolean usesDoubleValues() {
        return true;
    }

}
