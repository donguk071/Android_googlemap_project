package com.example.porjectforfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class frag3 extends Fragment {

    private View view;
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3,container,false);
        mListView = (ListView) view.findViewById(R.id.listView);
        dataSetting();
        return view;
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        mMyAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_login), "   로그인", "우리어디가! 와 함께하기!! (구현예정)" );
        mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_logout), "   로그아웃" , "다음에 또 봐요!!  (구현예정)" );
        mMyAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_timeline), "   타임라인보기" , "지금까지 어디를 갔을까요?  (구현예정)" );
        mMyAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_box), "   개발에 참여하기" , "github에 컨트리뷰션을 요청해주세요!!");
        mMyAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_box), "   코드보기" , "모든 코드는 오픈소스로 공개합니다!!");
        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

}
