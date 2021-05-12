//
// Decompiled by Jadx - 949ms
//
package com.robot;

import java.util.ArrayList;

public class SeasonInfo implements Comparable<SeasonInfo> {
    ArrayList<EpisodeInfo> episodes = new ArrayList<>();
    public int seasonindex;

    public SeasonInfo() {
    }

    
    public int compareTo(SeasonInfo seasonInfo) {
        if (this.seasonindex > seasonInfo.seasonindex) {
            return -1;
        }
        if (this.seasonindex == seasonInfo.seasonindex) {
            return 0;
        }
        return 1;
    }
}

