package com.example.android.ui.activity

import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.adapter.SongAdapter
import com.example.android.databinding.FragmentMusicBinding
import com.example.android.listeners.IOnClickSongItemListener
import com.example.android.model.Artist
import com.example.android.model.Song
import com.example.android.service.MusicService
import com.example.android.service.MusicServiceFunction


class MusicActivity() : AppCompatActivity(), View.OnClickListener {

    private var action = -1

    private val mBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            action = intent.getIntExtra(MusicService.MUSIC_ACTION, 0)
            handleMusicAction()
        }
    }

//    private lateinit var musicService: MusicService
//    private var serviceBound = false
//
//    private val serviceConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            val binder = service as MusicService.MusicBinder
//            musicService = binder.getService()
//            serviceBound = true
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//            serviceBound = false
//        }
//    }

    private fun handleMusicAction() {
        if (MusicService.action == MusicService.CANCEL) {
            binding.controlBottom.layoutItem.visibility = View.GONE
            return
        } else {
            binding.controlBottom.layoutItem.visibility = View.VISIBLE
            Log.e("receive", "visible")
            showInforSong()
            showStatusButtonPlay()
        }
    }

    private fun showStatusButtonPlay() {
        if (!MusicService.isPlaying) {
            binding.controlBottom.imgPlay.setImageResource(R.drawable.ic_play_music)
        } else {
            binding.controlBottom.imgPlay.setImageResource(R.drawable.ic_pause_music)
        }
    }

    private fun showInforSong() {
        if (MusicService.listSongPlaying == null || MusicService.listSongPlaying!!.isEmpty()) {
            return
        }
        val currentSong: Song = MusicService.listSongPlaying!![MusicService.songPosition]
        binding.controlBottom.tvSongName.text = currentSong.nameSong
        binding.controlBottom.tvArtist.text = currentSong.artistSongName
    }

    private lateinit var binding: FragmentMusicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LocalBroadcastManager.getInstance(this).registerReceiver(
            mBroadcastReceiver,
            IntentFilter(MusicService.CHANGE_LISTENER)
        )

//        val intent = Intent(this, MusicService::class.java)
//        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        displayListSongs()
    }

    private fun displayListSongs() {
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewSongs.layoutManager = layout
        val adapter = SongAdapter(object : IOnClickSongItemListener {
            override fun onClickItemArtist(artist: Artist) {
                TODO("Not yet implemented")
            }

            override fun onClickItemSong(song: Song) {
                goToSongDetail(song)
                Log.e("test", "itemClick")

            }

        })
        binding.recyclerViewSongs.adapter = adapter

        adapter.submitList(getSong())

    }

    private fun getSong(): List<Song> {
        val list = mutableListOf<Song>()

        list.add(
            Song(
                0,
                "https://vtv1.mediacdn.vn/zoom/640_400/2022/12/13/131222-adele-1670905139752141068015-crop-1670905144383835571127.png",
                "Hello",
                "Adele",
                "https://rr6---sn-42u-i5olk.googlevideo.com/videoplayback?expire=1697490607&ei=T1ItZf2nBoWx0-kPt-u_4A0&ip=113.23.6.106&id=o-AHVl7XyGB7WFrgAXgaDyZQBF90uOzHWCfbvJXxh4CGfY&itag=18&source=youtube&requiressl=yes&mh=cM&mm=31%2C26&mn=sn-42u-i5olk%2Csn-npoldn7l&ms=au%2Conr&mv=m&mvi=6&pl=24&initcwndbps=1373750&spc=UWF9f4wPhIF86h9vN-eeotQ69Vy9BMUII4UN_xx3Cg&vprv=1&svpuc=1&mime=video%2Fmp4&ns=PsU6N1b2Csj1giKHHwYESkwP&cnr=14&ratebypass=yes&dur=276.526&lmt=1686027428282798&mt=1697468705&fvip=4&fexp=24007246&beids=24350018&c=WEB&txp=4530434&n=ReoVO-KSh99cW8j&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Ccnr%2Cratebypass%2Cdur%2Clmt&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AK1ks_kwRgIhAMXaHnXn4umHl-QjUtKnI0LhINerE5nKud2LvFVt1c_lAiEAk68tsAutXOLlwEn0B4hMGkK_oFJP31ThzFMMmWSiAGc%3D&sig=AGM4YrMwRgIhAMlIiUzBfohSKqCT5uul5yyENywBk2R66232UGZqGlw5AiEAswyMkhpd_2RiYMTO4yBv9fSQGC7NP1CkbsrVjA-L-8o="
            )
        )

        list.add(
            Song(
                0,
                "https://vtv1.mediacdn.vn/zoom/640_400/2022/12/13/131222-adele-1670905139752141068015-crop-1670905144383835571127.png",
                "Hello",
                "Adele",
                "https://rr6---sn-42u-i5olk.googlevideo.com/videoplayback?expire=1697490607&ei=T1ItZf2nBoWx0-kPt-u_4A0&ip=113.23.6.106&id=o-AHVl7XyGB7WFrgAXgaDyZQBF90uOzHWCfbvJXxh4CGfY&itag=18&source=youtube&requiressl=yes&mh=cM&mm=31%2C26&mn=sn-42u-i5olk%2Csn-npoldn7l&ms=au%2Conr&mv=m&mvi=6&pl=24&initcwndbps=1373750&spc=UWF9f4wPhIF86h9vN-eeotQ69Vy9BMUII4UN_xx3Cg&vprv=1&svpuc=1&mime=video%2Fmp4&ns=PsU6N1b2Csj1giKHHwYESkwP&cnr=14&ratebypass=yes&dur=276.526&lmt=1686027428282798&mt=1697468705&fvip=4&fexp=24007246&beids=24350018&c=WEB&txp=4530434&n=ReoVO-KSh99cW8j&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Ccnr%2Cratebypass%2Cdur%2Clmt&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AK1ks_kwRgIhAMXaHnXn4umHl-QjUtKnI0LhINerE5nKud2LvFVt1c_lAiEAk68tsAutXOLlwEn0B4hMGkK_oFJP31ThzFMMmWSiAGc%3D&sig=AGM4YrMwRgIhAMlIiUzBfohSKqCT5uul5yyENywBk2R66232UGZqGlw5AiEAswyMkhpd_2RiYMTO4yBv9fSQGC7NP1CkbsrVjA-L-8o="
            )
        )

        return list
    }

    private fun goToSongDetail(song: Song) {
        MusicService.clearListSongPlaying()
        MusicService.listSongPlaying?.add(song)
        MusicService.isPlaying = false
        MusicServiceFunction().startMusicService(this, MusicService.PLAY, 0)
//        musicServiceFunction.startActivity(getActivity(), PlayMusicActivity::class.java)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.img_previous -> clickOnPrevButton()
            R.id.img_play -> clickOnPlayButton()
            R.id.img_next -> clickOnNextButton()
            R.id.img_close -> clickOnCloseButton()
            R.id.layout_text, R.id.img_song -> openPlayMusicActivity()
        }
    }

    private fun openPlayMusicActivity() {

    }

    private fun clickOnCloseButton() {
        MusicServiceFunction().startMusicService(
            this,
            MusicService.CANCEL,
            MusicService.songPosition
        )
        Log.e("test", "closeClick")
    }

    private fun clickOnNextButton() {
        MusicServiceFunction().startMusicService(this, MusicService.NEXT, MusicService.songPosition)
    }

    private fun clickOnPlayButton() {
        if (MusicService.isPlaying) {
            MusicServiceFunction().startMusicService(
                this,
                MusicService.PAUSE,
                MusicService.songPosition
            )
        } else {
            MusicServiceFunction().startMusicService(
                this,
                MusicService.RESUME,
                MusicService.songPosition
            )
        }
    }

    private fun clickOnPrevButton() {
        MusicServiceFunction().startMusicService(
            this,
            MusicService.PREVIOUS,
            MusicService.songPosition
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver)
    }

}
