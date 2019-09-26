package com.testytest.cr_lifehack_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.testytest.cr_lifehack_1.banner.BannerHelperFr;

import static com.testytest.cr_lifehack_1.One_Activity.PAGE_COUNT;

public class PageFragment_two extends Fragment {
    Button backToStartB;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    View view = null;
    private BannerHelperFr mBannerHelper;

    static PageFragment_two newInstance(int page) {
        PageFragment_two pageFragment2 = new PageFragment_two();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment2.setArguments(arguments);
        return pageFragment2;
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
                view = inflater.inflate(R.layout.fragment_two_0, null);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_two_1, null);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_two_2, null);
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_two_3, null);
                break;
            case 4:
                view = inflater.inflate(R.layout.fragment_two_4, null);
                break;
            case 5:
                view = inflater.inflate(R.layout.fragment_two_5, null);
                break;
            case 6:
                view = inflater.inflate(R.layout.fragment_two_6, null);
                break;
            case 7:
                view = inflater.inflate(R.layout.fragment_two_7, null);
                break;
            case 8:
                view = inflater.inflate(R.layout.fragment_two_8, null);
                break;
            case 9:
                view = inflater.inflate(R.layout.fragment_two_9, null);
                break;
            case 10:
                view = inflater.inflate(R.layout.fragment_two_10, null);
                break;
        }
        if (pageNumber < PAGE_COUNT - 1) {
            Button btn_next = (Button) view.findViewById(R.id.next_button);
            btn_next.setOnClickListener(twoBtnListener );
            Button btn_back = (Button) view.findViewById(R.id.back_button);
            btn_back.setOnClickListener(twoBtnListener );
//            backToStartB = (Button) view.findViewById(R.id.button_back);
//            backToStartB.setOnClickListener(twoBtnListener);
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mBannerHelper = new BannerHelperFr(this);
    }

    private View.OnClickListener twoBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.next_button:
                    mBannerHelper.setIBanner(() -> {
                    ((Two_Activity) getActivity()).pager.setCurrentItem(pageNumber+1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
                case R.id.back_button:
                    mBannerHelper.setIBanner(() -> {
                    ((Two_Activity) getActivity()).pager.setCurrentItem(pageNumber-1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
//                case R.id.button_back:
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    startActivity(intent);
//                    break;
            }
        }
    };

}

