package com.testytest.cr_lifehack_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.testytest.cr_lifehack_1.banner.BannerHelperFr;

import static com.testytest.cr_lifehack_1.One_Activity.PAGE_COUNT;

public class PageFragment_three extends Fragment {
    Button backToStartB;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    View view = null;
    private BannerHelperFr mBannerHelper;

    static PageFragment_three newInstance(int page) {
        PageFragment_three pageFragment3 = new PageFragment_three();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment3.setArguments(arguments);
        return pageFragment3;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (pageNumber) {
            case 0:
                view = inflater.inflate(R.layout.fragment_three_0, null);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_three_1, null);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_three_2, null);
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_three_3, null);
                break;
            case 4:
                view = inflater.inflate(R.layout.fragment_three_4, null);
                break;
        }
        if (pageNumber < PAGE_COUNT - 1) {
            Button btn_next = (Button) view.findViewById(R.id.next_button);
            btn_next.setOnClickListener(threeBtnListener );
            Button btn_back = (Button) view.findViewById(R.id.back_button);
            btn_back.setOnClickListener(threeBtnListener );
//            backToStartB = (Button) view.findViewById(R.id.button_back);
//            backToStartB.setOnClickListener(threeBtnListener);
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mBannerHelper = new BannerHelperFr(this);
    }

    private View.OnClickListener threeBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.next_button:
                    mBannerHelper.setIBanner(() -> {
                    ((Three_Activity) getActivity()).pager.setCurrentItem(pageNumber+1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
                case R.id.back_button:
                    mBannerHelper.setIBanner(() -> {
                    ((Three_Activity) getActivity()).pager.setCurrentItem(pageNumber-1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
//                case R.id.button_back:
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    startActivity(intent);
            }
        }
    };
}

