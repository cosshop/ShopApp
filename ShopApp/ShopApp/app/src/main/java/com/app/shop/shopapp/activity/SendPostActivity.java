package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.base.activity.BaseActivity;
import com.app.shop.shopapp.config.AppDir;
import com.app.shop.shopapp.fragment.DialogFragment;
import com.app.shop.shopapp.utils.FileUtil;
import com.app.shop.shopapp.utils.ImgUtils;
import com.app.shop.shopapp.utils.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/11 14:01
 * @version: V1.0
 */
public class SendPostActivity extends BaseActivity implements  DialogFragment.IDialogCallBack  {
    private static final int CITY_CHOICE_REQUEST = 33;
    /**
     * 修改密码
     */
    private static final int MODIFY_PWD_REQUEST = 44;
    private final static int CROP = 300;
    private static final String TAG = "UserInfoActivity";
    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + File.separator + AppDir.getDir(AppDir.AppDirEnum.UPLOAD_IMAGE_TEMP);
    private Uri origUri;
    private Uri cropUri;
    private File protraitFile;
    private Bitmap protraitBitmap;
    private String protraitPath;
    private String theLarge;
    private ImageView iv_image_1;
    private ImageView iv_image_2;
    private int clickWhich=1;
    @Override
    protected int bindLayout() {
        return R.layout.activity_send_post;
    }

    @Override
    public void initView() {
        super.initView();
        setTheme(R.style.ActionSheetStyleIOS7);
        TextView tv_right=findViewByIdU(R.id.tv_right);
        tv_right.setOnClickListener(this);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("发布");
        iv_image_1=findViewByIdU(R.id.iv_image_1);
        iv_image_2=findViewByIdU(R.id.iv_image_2);
    }

    @Override
    public void initData() {
        super.initData();
        setTitle("发帖");

    }

    @Override
    public void setListener() {
        super.setListener();
        iv_image_1.setOnClickListener(this);
        iv_image_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_right:
                ToastUtil.showToast("发布成功");
                break;
            case R.id.iv_image_1:
                clickWhich=1;
                DialogFragment.createBuilder(this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).setOtherButtonTitles(getResources().getStringArray(R.array.ps_select)).show();
                break;
            case R.id.iv_image_2:
                clickWhich=2;
                DialogFragment.createBuilder(this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).setOtherButtonTitles(getResources().getStringArray(R.array.ps_select)).show();
                break;
        }
    }

    @Override
    public void onDismiss(DialogFragment dialogFragment, boolean isCancel) {

    }
    public static final int ACTION_TYPE_ALBUM = 0;
    public static final int ACTION_TYPE_PHOTO = 1;

    private void goToSelectPicture(int position) {
        switch (position) {
            case ACTION_TYPE_ALBUM:
                startImagePick();
                break;
            case ACTION_TYPE_PHOTO:
                startTakePhoto();
                break;
            default:
                break;
        }
    }
    /**
     * 选择图片裁剪
     */
    private void startImagePick() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImgUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImgUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        }
    }
    private void startTakePhoto() {
        Intent intent;
        // 判断是否挂载了SD卡
        String savePath = "";
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath =Environment.getExternalStorageDirectory()
                    .getAbsolutePath()+"/"+ AppDir.getDir(AppDir.AppDirEnum.UPLOAD_IMAGE_TEMP);
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (TextUtils.isEmpty(savePath)) {
            ToastUtil.showToastLong("无法保存照片，请检查SD卡是否挂载");
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String fileName = "troust_" + timeStamp + ".jpg";// 照片命名
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);
        origUri = uri;

        theLarge = savePath + fileName;// 该照片的绝对路径

        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,
                ImgUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }
    @Override
    public void onOtherButtonClick(DialogFragment dialogFragment, int index) {
        goToSelectPicture(index);

    }
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode) {
            case ImgUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                startActionCrop(origUri);// 拍照后裁剪
                break;
            case ImgUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                startActionCrop(intent.getData());// 选图后裁剪
                break;
            case ImgUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                uploadNewPhoto();
                break;
            case CITY_CHOICE_REQUEST:
                break;
        }
    }

    // 裁剪头像的绝对路径
    private Uri getUploadTempFile(Uri uri) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            ToastUtil.showToast("无法保存上传的头像，请检查SD卡是否挂载");
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String thePath = ImgUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (TextUtils.isEmpty(thePath)) {
            thePath = ImgUtils.getAbsoluteImagePath(this, uri);
        }
        String ext = FileUtil.getFileFormat(thePath);
        ext = TextUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        String cropFileName = "troust_crop_" + timeStamp + "." + ext;
        // 裁剪头像的绝对路径
        protraitPath = FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);

        cropUri = Uri.fromFile(protraitFile);
        return this.cropUri;
    }
    /**
     * 上传新照片
     */
    private void uploadNewPhoto() {
        // 获取头像缩略图
        if (!TextUtils.isEmpty(protraitPath) && protraitFile.exists()) {
            protraitBitmap = ImgUtils.loadImgThumbnail(protraitPath, 300, 300);
            if(clickWhich==1){
                iv_image_1.setImageBitmap(protraitBitmap);
                if(iv_image_2.getVisibility()==View.GONE)iv_image_2.setVisibility(View.VISIBLE);

            }else{
                iv_image_2.setImageBitmap(protraitBitmap);
            }
        } else {
            ToastUtil.showToastLong("图像不存在，上传失败");
        }
    }

    /**
     * 拍照后裁剪
     *
     * @param data 原始图片
     *             裁剪后图片
     */
    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", CROP);// 输出图片大小
        intent.putExtra("outputY", CROP);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                ImgUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

}
