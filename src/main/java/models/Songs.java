package models;

public class Songs {
    private String tableName;
    private int songs_id;
    private String songName;
    private String songArtist;

    public Songs(String tableName, int songs_id, String songName, String songArtist) {
        this.tableName = tableName;
        this.songs_id = songs_id;
        this.songName = songName;
        this.songArtist = songArtist;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getSongs_id() {
        return songs_id;
    }

    public void setSongs_id(int songs_id) {
        this.songs_id = songs_id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }
}