package me.toddpickell.giddygoat.test;

import me.toddpickell.giddygoat.MainActivity;
import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private static final String CARD_FILENAME = "punch_card";
	private static final int MODE_PRIVATE = 0;
	private MainActivity mActivity;
	private Button mButton;
	private TextView mPunchCount;
	private SharedPreferences punchCard;
	private TextView mTextMarque;
	private String punchesFromCard;
	private View customAlertView;

	@SuppressWarnings("deprecation")
	public MainActivityTest() {
		super("me.toddpickell.giddygoat", MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		setActivityInitialTouchMode(false);
		mActivity = getActivity();
		
		mTextMarque = (TextView) mActivity.findViewById(me.toddpickell.giddygoat.R.id.mytextview);
		mPunchCount = (TextView) mActivity.findViewById(me.toddpickell.giddygoat.R.id.textView1);
		mButton = (Button) mActivity.findViewById(me.toddpickell.giddygoat.R.id.button1);
		
		punchCard = mActivity.getSharedPreferences(CARD_FILENAME, MODE_PRIVATE);
		punchesFromCard = Integer.toString(punchCard.getInt("punches", 50));
		
		customAlertView = (View) mActivity.findViewById(me.toddpickell.giddygoat.R.layout.customalert);
		
	}
	
//	@Test
	public void testPreConditions() throws Exception {
		assertNotNull(getActivity());
		assertNotNull(mButton);
		assertNotNull(mPunchCount);
		assertNotNull(mTextMarque);
		assertNotNull(punchCard);
	}

//	@Test
	public void testViewsAreVisible() throws Exception {
		ViewAsserts.assertOnScreen(mButton.getRootView(), mButton);
		ViewAsserts.assertOnScreen(mPunchCount.getRootView(), mPunchCount);
		ViewAsserts.assertOnScreen(mTextMarque.getRootView(), mTextMarque);
	}
	
//	@Test
	public void testPunchCountTextIsCorrect() throws Exception {
		assertEquals(punchesFromCard, mPunchCount.getText()); 
	}
	
//	@Test
	public void testPunchButtonLaunchesZBarScanner() throws Exception {
		TouchUtils.tapView(this, mButton);
		//assert scanner launches
		//assert that with 10 punches the popup launches
		if (punchCard.getInt("punches", 50) == 10) {
			ViewAsserts.assertOnScreen(customAlertView.getRootView(), customAlertView);
		} 
		
	}
}











