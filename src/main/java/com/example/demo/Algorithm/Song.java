package com.example.demo.Algorithm;

import java.util.HashSet;
import java.util.Set;

public class Song {

    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {

        Set<String> songSet = new HashSet<String>();

        Song nextSong = this.nextSong;

        while (nextSong != null) {
            boolean result = songSet.add(nextSong.name);
            if (result == false) {
                return true;
            }
            nextSong = nextSong.nextSong;
        }

        return false;

    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}
