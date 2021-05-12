//
// Decompiled by Jadx - 840ms
//
package com.robot;

public class EpisodeInfo implements Comparable<EpisodeInfo> {
    public String airDate;
    public String download;
    public int episodeIndex;
    public String episodeTitle;
    public long fileSize;
    public int seasonIndex;

    public EpisodeInfo() {
    }

    

    public int compareTo(EpisodeInfo episodeInfo) {
        if (this.episodeIndex > episodeInfo.episodeIndex) {
            return -1;
        }
        if (this.episodeIndex == episodeInfo.episodeIndex) {
            return 0;
        }
        return 1;
    }
}

