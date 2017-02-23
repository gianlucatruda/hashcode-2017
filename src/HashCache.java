import java.util.ArrayList;

public class HashCache {
    public int size; // The size of the cache in MB.
    public int ID;
    public ArrayList<HashVideo> videos = new ArrayList<>();
    public int fullness;

    public HashCache(int id, int s) {
        this.ID = id;
        this.size = s;
        this.fullness = 0;
    }

    public void setVideos(ArrayList<HashVideo> vids) {
        for (HashVideo v : vids) {
            videos.add(v);
        }
    }

    public ArrayList<HashVideo> getVideos() {
        return videos;
    }
}