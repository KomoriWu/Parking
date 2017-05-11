package com.example.parking.utils;


import android.content.Context;
import android.content.Intent;

import android.Manifest;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.amap.api.navi.model.NaviLatLng;
import com.example.parking.R;

import com.example.parking.add.navi.NavigationActivity;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public class Utils {
    public static final String DISTANCE_MIN = "distance_min";
    public static final String DISTANCE_MAX = "distance_max";
    public static final String PARKING = "parking";
    public static final String ACCOUNT = "account";
    public static final String TEXT_POI_KEY = "停车场";
    public static final String TEXT_POI_TYPE = "交通设施服务";
    public static final int POI_SEARCH_BOUND = 10000;

    public static final int POI_SEARCH_COUNT = 30;
    //讯飞语音
    public static final String XUN_FEI_APP_ID = "57d0dad1";


    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_SHORT).setAction(R.string.know,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }


    //显示导航Activity
    public static void showNaviActivity(Context context, NaviLatLng startLatlng, NaviLatLng endLatlng) {
        Intent intent = new Intent(context, NavigationActivity.class);
        intent.putExtra(NavigationActivity.EXTRA_START_POINT, startLatlng);
        intent.putExtra(NavigationActivity.EXTRA_END_POINT, endLatlng);
        context.startActivity(intent);
    }


    public static String getPermission(int position) {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        return permissions[position];
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId) {
        return getImageOptions(defaultIconId, 0);
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId, int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .showImageOnLoading(defaultIconId)
                .showImageOnFail(defaultIconId)
                .showImageForEmptyUri(defaultIconId)
                .cacheInMemory(true)
                .cacheOnDisc()
                .build();
    }

    public static String getParkImaUrl(int position) {
        String[] strings = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492808510&di=51b23c2b15a2dce687690382d28fed20&imgtype=0&src=http%3A%2F%2Fpic33.photophoto.cn%2F20141117%2F0038038023474945_b.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495087560&di=9c0a974139a7c7c4eb7c8b0dbc73f5a3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg01.taopic.com%2F141117%2F240450-14111FJR929.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492835303&di=cd01da4996a795157b92ac08bc511684&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F160422%2F14-160422153553501.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492835303&di=6878dd41ea8e29dda4ea818244377a14&imgtype=0&src=http%3A%2F%2Fi2.f.itc.cn%2Fupload%2Fxa%2F6689%2Fa_66887537.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492835303&di=b0dd11927175ef8a64897d66e5282685&imgtype=0&src=http%3A%2F%2Fpic1.shejiben.com%2Fcase%2F2015%2F05%2F30%2F20150530124430-7349cfbf.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895985&di=d374b6fb7b992f60e35fe64002378496&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Flvpics%2Fw%3D1000%2Fsign%3De3bc4a27d588d43ff0a995f24d2ed31b%2Ff636afc379310a55aca633a6b44543a9832610e5.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895984&di=e3ff3f08ff9b22ca00a984e82ce3b131&imgtype=0&src=http%3A%2F%2Fscimg.158sucai.com%2Fallimg%2F160627%2F14-16062F9110W61.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895984&di=2b265527531e87f6e7576b9944669c36&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20140903%2Fshulinzhongdexiaoyuantingchechang_3010373.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895983&di=50d0623ab677f7d97fdd801627d31942&imgtype=0&src=http%3A%2F%2Fpic76.huitu.com%2Fres%2F20160525%2F169904_20160525005529418370_1.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895983&di=c08fa8210f237ee12529b7cd888feb18&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150420%2Fdaxinglutiantingchechang_4186642.jpg",

                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895983&di=a9a4aeb40c8f9daf3c0800ef8747a021&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150420%2Ffushidaxinglutiantingchechang_4186646.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895983&di=ba0f484fd31c61f3d46a0fe5939eef76&imgtype=0&src=http%3A%2F%2Fimg.qqzhi.com%2Fupload%2Fimg_2_840427744D1430719768_11.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895983&di=78d7daf502498b5c8f6713c10307f1d8&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F141117%2F240450-14111FKR98.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895983&di=36766cb795669a2c8e5d3e3676b539af&imgtype=0&src=http%3A%2F%2Fpic1.shejiben.com%2Fi%2Fupload%2F2015%2F05%2F30%2F20150530124430-7349cfbf-me.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895982&di=5afd9b4801c7bfa5d2637af87a0498fb&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Fe03a6353238fe5766159f3c2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895982&di=ee5bd6eedbd6d44297c4ff0b8ec6c484&imgtype=0&src=http%3A%2F%2Fimage.cn.made-in-china.com%2F2f0j01lvKtEUHCgqrF%2F%25E5%2581%259C%25E8%25BD%25A6%25E5%259C%25BA%25E5%25B9%25B3%25E5%258F%25B0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895982&di=9a6169e587af6ee3f9fcf14ba1055e7b&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Flvpics%2Fw%3D600%2Fsign%3D983a5b14b8a1cd1105b671208913c8b0%2Fd833c895d143ad4bb1f5f9e480025aafa40f063e.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895982&di=755342a9b0b6f58af2cd309fa5139969&imgtype=0&src=http%3A%2F%2Fimages.quanjing.com%2Fchineseview064%2Fhigh%2Fmhrm-dspd26698.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494493068215&di=ee12b3818bd13f5f2c0ad2721e38679c&imgtype=0&src=http%3A%2F%2Fpic.qjimage.com%2Ftop037%2Fhigh%2Ftop-947572.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895981&di=d5aae332be94fe3c3f78a46c83c82297&imgtype=0&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_4_3572900405D2596794441_23.jpg",

                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895980&di=be0dbaaff75be06f9b42413887392e3c&imgtype=0&src=http%3A%2F%2Fpic33.photophoto.cn%2F20141014%2F0016028599541781_b.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895980&di=a2499748eb8a139604e42e49c04eb6b4&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F121210%2F267867-12121014401114.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895980&di=0dc67500147993bede4d907f20eecba4&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20160712%2Ftooopen_sy_170093323569.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895980&di=cbc7f755106356b067fbc670f5ce5e9e&imgtype=0&src=http%3A%2F%2Ffile2.zhituad.com%2Fthumb%2F201304%2F16%2F201304160302433706YBRT6_priv.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895979&di=3c46f9a68ba282ce1cbd0859d19ff408&imgtype=0&src=http%3A%2F%2Fimg1.lvyou114.com%2FTukuMax%2F73%2F201371815912.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895977&di=446c064ee4fa6f2ee03b11eb7b6ea41d&imgtype=0&src=http%3A%2F%2Fimg000.hc360.cn%2Fm8%2FM00%2F0F%2FF6%2FwKhQpVdSVsWEbozzAAAAAF1LrJw416.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494492895976&di=79179721b96dfe939a8d93bb7fd46aab&imgtype=0&src=http%3A%2F%2Fpic.qjimage.com%2Fchineseview125%2Fhigh%2Fph1255-0841.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494493068224&di=d59f6f04b2f44285743ba4e4111bb7cc&imgtype=0&src=http%3A%2F%2Fpic.qjimage.com%2Ftop024%2Fhigh%2Ftop-818306.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494493068223&di=7161439bd405f6df729de269f2ef8ab4&imgtype=0&src=http%3A%2F%2Fimg1.juimg.com%2F141107%2F330478-14110F9241912.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494493068223&di=fbf04bf1c32c99a9f7d7fb3712d32390&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F141117%2F240450-14111FP14977.jpg"};
        return strings[position];
    }
}
