package eu.syrou.hodor;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HodorStatesaveTestView extends LinearLayout {
    LinearLayout mRootView;
    int deviceWidth;

    public HodorStatesaveTestView(Context context) {
        this(context, null, 0);
    }

    public HodorStatesaveTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HodorStatesaveTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context);
    }

    public HodorStatesaveTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateLayout(context);
    }

    private EditText getEdittext(){
        return (EditText) mRootView.findViewById(R.id.edittext);
    }

    private CheckBox getCheckbox(){
        return (CheckBox) mRootView.findViewById(R.id.checkbox);
    }

    public TextView getTextView(){
        return (TextView) mRootView.findViewById(R.id.text);
    }

    private void inflateLayout(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = (LinearLayout) layoutInflater.inflate(R.layout.hodor_statesave_test, this);
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        deviceWidth = deviceDisplay.x;
        //addView(mRootView);
    }
}
