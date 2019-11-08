package com.youngstudio.oemarket;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

import static android.app.Activity.RESULT_OK;

public class WritingFragment extends Fragment {

    EditText writing_et_price,writing_ev_title,writing_et_mainmsg;
    ImageView iv;
    String imgPath;

    Button clickBtn;
    ImageButton btnCamara;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        startActivity(new Intent(getActivity(),ClickActivity.class));
//        getActivity().finish();

        writing_et_price= getActivity().findViewById(R.id.writing_et_price);
        writing_ev_title= getActivity().findViewById(R.id.writing_ev_title);
        writing_et_mainmsg= getActivity().findViewById(R.id.writing_et_mainmsg);
        iv= getActivity().findViewById(R.id.iv);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view= inflater.inflate(R.layout.fragment_writing, container, false);

        clickBtn= view.findViewById(R.id.clickBtn);
        btnCamara= view.findViewById(R.id.writing_iv_camera);

//        clickBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //서버로 보낼 데이터
//                String title= writing_ev_title.getText().toString();
//                String mainMsg= writing_et_mainmsg.getText().toString();
//
//                //안드로이드에서 보낼 데이터를 받을 php서버주소
//                String serverUrl="http://rmflawkdk.dothome.co.kr/Android/insertDB.php";
//
//                //파일전송요청 객체 생성[결과를 String으로 받음]
//                SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        new AlertDialog.Builder(getActivity()).setMessage("응답:"+response).create().show();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //요청객체에 보낼 데이터를 추가
//                smpr.addStringParam("name", title);
//                smpr.addStringParam("msg", mainMsg);
//                //이미지파일 추가
//                smpr.addFile("img", imgPath);
//
//                //요청객체를 서버로 보낼 우체통같은 객체 생성
//                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
//                requestQueue.add(smpr);
//
//
//            }
//        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리 or 사진 앱 실행하여 사진을 선택하도록..
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 10);
            }
        });


        return view;

    }//onCreateView


    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aa:
                Toast.makeText(getActivity(), "menu aa", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
                        new AlertDialog.Builder(getActivity()).setMessage(uri.toString()+ "\n"+imgPath).create().show();
                    }
                }else{
                    Toast.makeText(getActivity(), "이미지 선택을 하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }//onActivityResult

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(getActivity(), uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }





}//class






//    public void clickCamera(View v) {
//        //갤러리 or 사진 앱 실행하여 사진을 선택하도록..
//        Intent intent= new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, 10);
//    }

//    public void clickUpload(View v) {
//        //서버로 보낼 데이터
//        String title= writing_ev_title.getText().toString();
//        String mainMsg= writing_et_mainmsg.getText().toString();
//
//        //안드로이드에서 보낼 데이터를 받을 php서버주소
//        String serverUrl="http://rmflawkdk.dothome.co.kr/Android/insertDB.php";
//
//        //파일전송요청 객체 생성[결과를 String으로 받음]
//        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                new AlertDialog.Builder(getActivity()).setMessage("응답:"+response).create().show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //요청객체에 보낼 데이터를 추가
//        smpr.addStringParam("name", title);
//        smpr.addStringParam("msg", mainMsg);
//        //이미지파일 추가
//        smpr.addFile("img", imgPath);
//
//        //요청객체를 서버로 보낼 우체통같은 객체 생성
//        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
//        requestQueue.add(smpr);
//
//
//
//    }//clickUpload



    //    //위 onCreateView가 실행된 후 자동 실행되는 메소드
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        //리스트뷰의 아이템을 클릭하면...
////        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
////                Toast.makeText(getActivity(), datas.get(position), Toast.LENGTH_SHORT).show();
////            }
////        });
//    }


