package com.youngstudio.oemarket;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

public class WritingActivity extends AppCompatActivity {

    public EditText writing_et_price,writing_ev_title,writing_et_mainmsg;
    public ImageView iv;
    public String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        writing_et_price= findViewById(R.id.writing_et_price);
        writing_ev_title= findViewById(R.id.writing_ev_title);
        writing_et_mainmsg= findViewById(R.id.writing_et_mainmsg);
        iv= findViewById(R.id.writing_iv);


        //액션바에 제목이 자동표시 되지 않도록
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //업로드하려면 외부저장소 권한 필요
        //동적퍼미션 코드 필요...
        // 외부저장소 권한 동적퍼미션
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            int permissionResult= checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            if(permissionResult == PackageManager.PERMISSION_DENIED){
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10);
//            }
//        }

    }


    public void clickCamera(View view) {
        //갤러리 or 사진 앱 실행하여 사진을 선택하도록..
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    //선택한 사진의 경로(Uri)객체 얻어오기
                    Uri uri= data.getData();
                    if(uri != null){
                        iv.setImageURI(uri);

                        //얻어온 uri는 Gallery앱의 DB번호임.
                        //업로드를 하려면 이미지의 절대경로(실제경로 : file://storage/emulated/0/Download/aaaa.png)가 필요함.
                        //uri -> 절대경로(String)로 변환
                        imgPath= getRealPathFromUri(uri);

                        //이미지 경로 Uri 확인해보기
                        new AlertDialog.Builder(this).setMessage(uri.toString()+ "\n"+imgPath).create().show();
                    }
                }else{
                    Toast.makeText(this, "이미지 선택을 하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }//onActivityResult

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }


    public void clickUpload(View view) {
        //서버로 보낼 데이터
        String name= writing_ev_title.getText().toString();
        String msg= writing_et_mainmsg.getText().toString();
        int price= Integer.parseInt(writing_et_price.getText().toString());

        //안드로이드에서 보낼 데이터를 받을 php서버주소
        String serverUrl="http://rmflawkdk.dothome.co.kr/Android/insertDB.php";
        //Log.i("tag","a");

        //Volley plus library를 이용해서 파일전송하도록
        //Volley+는 AndroidStudio에서 검색안됨[google검색 이용]

        //파일전송요청 객체 생성[결과를 String으로 받음]
        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new AlertDialog.Builder(WritingActivity.this).setMessage("응답:"+response).create().show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WritingActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //요청객체에 보낼 데이터를 추가
        smpr.addStringParam("name", name);
        smpr.addStringParam("msg", msg);
        smpr.addStringParam("price", price+"");
        //이미지파일 추가
        smpr.addFile("img", imgPath);

        //요청객체를 서버로 보낼 우체통같은 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(smpr);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //startActivity(new Intent(this, MainActivity.class));
        //finish();
    }
}//class