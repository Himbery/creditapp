package com.testytest.cr_lifehack_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.testytest.cr_lifehack_1.banner.BannerHelperFr;

import static com.testytest.cr_lifehack_1.One_Activity.PAGE_COUNT;

public class PageFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    View view = null;
    private BannerHelperFr mBannerHelper;

    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
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
                view = inflater.inflate(R.layout.fragment_0, null);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_1, null);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_2, null);
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_3, null);
                break;
            case 4:
                view = inflater.inflate(R.layout.fragment_4, null);
                break;
            case 5:
                view = inflater.inflate(R.layout.fragment_5, null);
                break;
            case 6:
                view = inflater.inflate(R.layout.fragment_6, null);
                break;
            case 7:
                view = inflater.inflate(R.layout.fragment_7, null);
                break;
            case 8:
                view = inflater.inflate(R.layout.fragment_8, null);
                break;
            case 9:
                view = inflater.inflate(R.layout.fragment_9, null);
                break;
            case 10:
                view = inflater.inflate(R.layout.fragment_10, null);
                break;
            case 11:
                view = inflater.inflate(R.layout.fragment_11, null);
                break;
            case 12:
                view = inflater.inflate(R.layout.fragment_12, null);
                break;
            case 13:
                view = inflater.inflate(R.layout.fragment_13, null);
                break;
            case 14:
                view = inflater.inflate(R.layout.fragment_14, null);
                break;
            case 15:
                view = inflater.inflate(R.layout.fragment_15, null);
                break;
            case 16:
                view = inflater.inflate(R.layout.fragment_16, null);
                break;
            case 17:
                view = inflater.inflate(R.layout.fragment_17, null);
                break;
            case 18:
                view = inflater.inflate(R.layout.fragment_18, null);
                break;
            case 19:
                view = inflater.inflate(R.layout.fragment_19, null);
                break;
            case 20:
                view = inflater.inflate(R.layout.fragment_20, null);
                break;
            case 21:
                view = inflater.inflate(R.layout.fragment_21, null);
                break;
            case 22:
                view = inflater.inflate(R.layout.fragment_22, null);
                break;
            case 23:
                view = inflater.inflate(R.layout.fragment_23, null);
                break;
            case 24:
                view = inflater.inflate(R.layout.fragment_24, null);
                break;
            case 25:
                view = inflater.inflate(R.layout.fragment_25, null);
                break;
            case 26:
                view = inflater.inflate(R.layout.fragment_26, null);
                break;
            case 27:
                view = inflater.inflate(R.layout.fragment_27, null);
                break;
            case 28:
                view = inflater.inflate(R.layout.fragment_28, null);
                break;
            case 29:
                view = inflater.inflate(R.layout.fragment_29, null);
                break;
            case 30:
                view = inflater.inflate(R.layout.fragment_30, null);
                break;
            case 31:
                view = inflater.inflate(R.layout.fragment_31, null);
                break;
            case 32:
                view = inflater.inflate(R.layout.fragment_32, null);
                break;
            case 33:
                view = inflater.inflate(R.layout.fragment_33, null);
                break;
            case 34:
                view = inflater.inflate(R.layout.fragment_34, null);
                break;
            case 35:
                view = inflater.inflate(R.layout.fragment_35, null);
                break;
            case 36:
                view = inflater.inflate(R.layout.fragment_36, null);
                break;
            case 37:
                view = inflater.inflate(R.layout.fragment_37, null);
                break;
            case 38:
                view = inflater.inflate(R.layout.fragment_38, null);
                break;
            case 39:
                view = inflater.inflate(R.layout.fragment_39, null);
                break;
            case 40:
                view = inflater.inflate(R.layout.fragment_40, null);
                break;
            case 41:
                view = inflater.inflate(R.layout.fragment_41, null);
                break;
            case 42:
                view = inflater.inflate(R.layout.fragment_42, null);
                break;
            case 43:
                view = inflater.inflate(R.layout.fragment_43, null);
                break;
        }
        if (pageNumber < PAGE_COUNT - 1) {
            Button btn_next = (Button) view.findViewById(R.id.next_button);
            btn_next.setOnClickListener(navBarBtnListener );
            Button btn_back = (Button) view.findViewById(R.id.back_button);
            btn_back.setOnClickListener(navBarBtnListener );
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mBannerHelper = new BannerHelperFr(this);
    }

    private View.OnClickListener navBarBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.next_button:
                    mBannerHelper.setIBanner(() -> {
                    ((One_Activity) getActivity()).pager.setCurrentItem(pageNumber+1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
                case R.id.back_button:
                    mBannerHelper.setIBanner(() -> {
                    ((One_Activity) getActivity()).pager.setCurrentItem(pageNumber-1, true);
                    });
                    mBannerHelper.showBanner();
                    break;
            }
        }
    };
}
