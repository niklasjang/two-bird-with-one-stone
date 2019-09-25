package com.example.twobirdwithonestone.Activity;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

public class CouponRecyclerViewAdapter extends RecyclerView.Adapter<CouponRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Coupon> mData = null ;
    private final Context context;
    private PopupWindow mPopupWindow;
    // 생성자에서 데이터 리스트 객체를 전달받음.
    public CouponRecyclerViewAdapter(Context _context, ArrayList<Coupon> list) {
        context = _context;
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public CouponRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false) ;
        return new ViewHolder(view) ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Coupon coupon = mData.get(position) ;
        Log.d("CouponRecyclerView", Integer.toString(position));
        Log.d("CouponRecyclerView", Integer.toString(mData.size()));
        Log.d("CouponRecyclerView", coupon.couponCreateTime);
        /**
         * 문화생활  100  :101~105
         * 기부      200 : 201~205
         * 커피/음료 300  : 301~303
         * 캠핑      400  : 401~405
         * 케이크    500  : 501~502
         * 기타      600  : 601
         * */
        switch(coupon.couponImgIndex){
            case 101:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_culture_1);
                break;
            case 102:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_culture_2);
                break;
            case 103:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_culture_3);
                break;
            case 104:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_culture_4);
                break;
            case 105:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_culture_5);
                break;
            case 201:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_fund_1);
                break;
            case 202:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_fund_2);
                break;
            case 203:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_fund_3);
                break;
            case 204:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_fund_4);
                break;
            case 205:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_fund_5);
                break;
            case 301:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_coffee_1);
                break;
            case 302:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_coffee_2);
                break;
            case 303:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_coffee_3);
                break;
            case 401:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_camp_1);
                break;
            case 402:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_camp_2);
                break;
            case 403:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_camp_3);
                break;
            case 404:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_camp_4);
                break;
            case 405:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_camp_5);
                break;
            case 501:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_cake_1);
                break;
            case 502:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_cake_2);
                break;
            case 601:
                holder.imgCoupon.setImageResource(R.drawable.ic_shop_etc_1);
                break;
            default:
                holder.imgCoupon.setImageResource(R.drawable.circle_logo_b);
        }
        holder.tvCouponName.setText(coupon.couponName);
        holder.tvCouponCreateTime.setText(coupon.couponCreateTime+" 생성");
        if(coupon.couponUesrOrNot){
            holder.btnCouponUse.setText("사용완료");
        }else{
            holder.btnCouponUse.setText("사용");
        }

        holder.btnCouponUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btnCouponUse.getText().equals("사용")){
                    //Popup window 설청
                    View popupView = LayoutInflater.from(context).inflate(R.layout.coupon_popup_window, null);
                    mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                      //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                    mPopupWindow.setFocusable(true); // 외부 영역 선택시 PopUp 종료
                    mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
//                    TextView tvPopupName = v.findViewById(R.id.tvPopupName);
//                    tvPopupName.setText(holder.tvCouponName.getText().toString());
                    //popup 취소 버튼 클릭시
                    Button cancel = (Button) popupView.findViewById(R.id.btnPopupCancel);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopupWindow.dismiss();
                        }
                    });

                    //popup 사용 버튼 클릭시
                    Button ok = (Button) popupView.findViewById(R.id.btnPopupUse);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "쿠폰을 사용합니다.", Toast.LENGTH_SHORT).show();
                            DataBase database = new DataBase();
                            database.updataCouponUsedOrNot("Coupons", coupon.getCouponUID(), coupon.couponUesrOrNot);
                            holder.btnCouponUse.setText("사용완료");
                            mPopupWindow.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(v.getContext(), "이미 사용된 쿠폰입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgCoupon;
        public TextView tvCouponName;
        public  TextView tvCouponCreateTime;
        public  Button btnCouponUse;

        public ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            imgCoupon = itemView.findViewById(R.id.imgCoupon);
            tvCouponName = itemView.findViewById(R.id.tvCouponName);
            tvCouponCreateTime = itemView.findViewById(R.id.tvCouponCreateTime);
            btnCouponUse = itemView.findViewById(R.id.btnCouponUse);
        }
    }
}