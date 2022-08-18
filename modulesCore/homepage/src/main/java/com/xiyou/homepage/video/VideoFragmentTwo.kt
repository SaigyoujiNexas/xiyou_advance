package com.xiyou.homepage.video

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.xiyou.homepage.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "videoData"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VideoFragmentTwo.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoFragmentTwo : Fragment() {
    // TODO: Rename and change types of parameters
    private var videoData: String? = null
    private var param2: String? = null
    private var webView:WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoData = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private val webChromeClient = object : WebChromeClient(){}
    private val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            val uri = Uri.parse(url)
            if (uri.scheme == "http" || uri.scheme == "https") {
                view.loadUrl(url!!)
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
            return true
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video_two, container, false)
        webView = view.findViewById<WebView>(R.id.web_video_fragment)
        val webSettings = webView!!.settings
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            webSettings.mixedContentMode=WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webSettings.defaultTextEncodingName = "utf-8"
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(true)//支持缩放
        webSettings.domStorageEnabled = true
        webView!!.webChromeClient = webChromeClient
        webView!!.webViewClient = webViewClient
        videoData?.let { webView!!.loadUrl(it) }
        return view;
    }

    override fun onDestroy() {
        super.onDestroy()
        if(webView !=null)  webView!!.destroy();
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VideoFragmentTwo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoFragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}