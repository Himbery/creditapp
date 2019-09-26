package com.testytest.cr_lifehack_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.testytest.cr_lifehack_1.banner.BannerHelperFr;

import static com.testytest.cr_lifehack_1.One_Activity.PAGE_COUNT;

public class PageFragment_five extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    View view = null;
    private BannerHelperFr mBannerHelper;

    static PageFragment_five newInstance(int page) {
        PageFragment_five pageFragment5 = new PageFragment_five();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment5.setArguments(arguments);
        return pageFragment5;
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
                view = inflater.inflate(R.layout.fragment_five_0, null);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_five_1, null);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_five_2, null);
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_five_3, null);
                break;
            case 4:
                view = inflater.inflate(R.layout.fragment_five_4, null);
                break;
            case 5:
                view = inflater.inflate(R.layout.fragment_five_5, null);
                break;
            case 6:
                view = inflater.inflate(R.layout.fragment_five_6, null);
                break;
            case 7:
                view = inflater.inflate(R.layout.fragment_five_7, null);
                break;
            case 8:
                view = inflater.inflate(R.layout.fragment_five_8, null);
                break;
            case 9:
                view = inflater.inflate(R.layout.fragment_five_9, null);
                break;
            case 10:
                view = inflater.inflate(R.layout.fragment_five_10, null);
                break;
        }
        if (pageNumber < PAGE_COUNT - 1) {
            Button btn_next = (Button) view.findViewById(R.id.next_button);
            btn_next.setOnClickListener(fiveBtnListener );
            Button btn_back = (Button) view.findViewById(R.id.back_button);
            btn_back.setOnClickListener(fiveBtnListener );

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerHelper = new BannerHelperFr(this);
    }

    private View.OnClickListener fiveBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.next_button:
                    mBannerHelper.setIBanner(() -> {
                    ((Five_Activity) getActivity()).pager.setCurrentItem(pageNumber+1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
                case R.id.back_button:
                    mBannerHelper.setIBanner(() -> {
                    ((Five_Activity) getActivity()).pager.setCurrentItem(pageNumber-1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
            }
        }
    };

}
