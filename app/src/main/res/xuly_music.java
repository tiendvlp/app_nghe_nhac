
public class xuly_music {
    private static MediaPlayer mediaPlayer;
    private static final xuly_music xuly_music=new xuly_music();
    // Tự tạo vòng đời
    private static final int IDLE=0;
    private static final int PLAYING=1;
    private static final int STOPPED=2;
    private static final int PAUSE=3;
    // ban đầu sẽ là idle
    private static int STATE=IDLE;
    private on_play_listener on_play_listener;

    private xuly_music () {
        mediaPlayer=new MediaPlayer();
    }

    public static xuly_music get_instance () {
            return xuly_music;
    }


    public void set_data_source(String song_name) throws IOException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Zingmp3/song/"+song_name+".mp3";
        // cách set resource siêu khủng
        stop_music();
        mediaPlayer.reset();
        mediaPlayer.setDataSource(path);
        // phải gọi lệnh prepare sau khi set resource
        mediaPlayer.prepare();
    }
    public void stop_music() {
        if (STATE==PLAYING || STATE==PAUSE) {
            mediaPlayer.stop();
            STATE=STOPPED;
        }
    }

    public void pause () {
        if (STATE == PLAYING) {
            mediaPlayer.pause();
            STATE=PAUSE;
        }
    }

    public void play() {
        if(STATE == IDLE || STATE == PAUSE || STATE == STOPPED) {
            mediaPlayer.start();
            on_play_listener.on_play();
            STATE=PLAYING;
        }}

    public void seek_to (int mili) {
        mediaPlayer.seekTo(mili);
    }

    public int get_duration () {
        // Lấy ra độ dài của bài hát
        return mediaPlayer.getDuration();
    }

    public int get_current_position () {
        // Lấy ra vị trí hiện tại của bài hát
        return mediaPlayer.getCurrentPosition();
    }

    public void reset() {
        // reset media (reset cả resource, phải set lại resource sau khi reset)
        mediaPlayer.reset();
    }
    // sự kiện khi play nhạc
    public void set_on_play_listener (on_play_listener on_play_listener) {
        this.on_play_listener=on_play_listener;
    }

    public interface on_play_listener {
        void on_play ();
    }
}
