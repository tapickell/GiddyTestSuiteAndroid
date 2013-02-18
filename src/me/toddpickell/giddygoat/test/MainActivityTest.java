package me.toddpickell.giddygoat.test;

import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.jayway.android.robotium.solo.Solo;

import me.toddpickell.giddygoat.DrinksMenu;
import me.toddpickell.giddygoat.MainActivity;
import android.app.Instrumentation;
import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
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
	private Instrumentation mInstrumentation;
	private Solo mSolo;

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
		mInstrumentation = getInstrumentation();
		mSolo = new Solo(mInstrumentation, mActivity);
		
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
	
//	@Test   /* this should be functional "user story" test  */
	public void test10thPunchLaunches10thPunchWarningOnPress() throws Exception {
		int temp = punchCard.getInt("punches", 50);
		SharedPreferences.Editor editor = punchCard.edit();
		editor.putInt("punches", 10);
		editor.commit();

		mSolo.clickOnView(mButton);	
		if (punchCard.getInt("punches", 50) == 10) {

			assertTrue(mSolo.searchText("Congrats on filling the punches"));
			mSolo.clickOnButton("Cancel");
		}
		
		editor.putInt("punches", temp);
		editor.commit();
	}
	
//	@Test    /* this should be functional "user story" test  */
	public void testThatPunchButtonLaunchesZBarScannerActivity() throws Exception {
		mSolo.clickOnView(mButton);
		mSolo.clickOnButton("Continue");
		mSolo.assertCurrentActivity("expected ZBarScannerActivity", ZBarScannerActivity.class);
		mSolo.goBack();
	}
	
//	@Test   /* this should be functional "user story" test  */
	public void testThatDrinksSelectionFromMenuLaunchesDrinksMenuActivity() throws Exception {
		mSolo.clickOnMenuItem("Drinks");
		mSolo.assertCurrentActivity("expected DrinksMenuActivity", DrinksMenu.class);
		mSolo.goBack();
	}
	
//	@Test   /* this should be functional "user story" test  */
	public void testThatPhoneSelectionFromMenuDisplaysCallingDialog() throws Exception {
		mSolo.clickOnMenuItem("Phone");
		assertTrue(mSolo.searchText("Calling 573-426-6750"));
		mSolo.goBack();
	}
	
//	@Test   /* this should be functional "user story" test  */
	public void testThatCreditsSelectionFromMenuDisplaysCreditsDialog() throws Exception {
		mSolo.clickOnMenuItem("Credits");
		assertTrue(mSolo.searchText("Developer: Todd Pickell"));
		mSolo.goBack();
	}
	
//	@Test   /* this should be functional "user story" test  */
	public void testThatMapSelectionFromMenuLaunchesGoogleMaps() throws Exception {
		mSolo.clickOnMenuItem("Map");
		mSolo.assertCurrentActivity("expected Google Map", MainActivity.class);
		mSolo.goBack();
	}
	
}











