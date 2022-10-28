package com.zezoo.qurankarem;

import java.io.File;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class List_Activity extends ListActivity {
	// StringArray
	String ifs_name[] = { "1- ���� �������", "2- ���� ������", "3- ���� �� �����", "4- ���� ������", "5- ���� �������",
			"6- ���� �������", "7- ���� �������", "8- ���� �������", "9- ���� ������", "10- ���� ����", "11- ���� ���",
			"12- ���� ����", "13- ���� �����", "14- ���� �������", "15- ���� �����", "16- ���� �����",
			"17- ���� �������", "18- ���� �����", "19- ���� ����", "20- ���� ��", "21- ���� ��������", "22- ���� ����",
			"23- ���� ��������", "24- ���� �����", "25- ���� �������", "26- ���� �������", "27- ���� �����",
			"28- ���� �����", "29- ���� ��������", "30- ���� �����", "31- ���� �����", "32- ���� ������",
			"33- ���� �������", "34- ���� ���", "35- ���� ����", "36- ���� ��", "37- ���� �������", "38- ���� �",
			"39- ���� �����", "40- ���� ����", "41- ���� ����", "42- ���� ������", "43- ���� ������", "44- ���� ������",
			"45- ���� �������", "46- ���� �������", "47- ���� ����", "48- ���� �����", "49- ���� �������", "50- ���� �",
			"51- ���� ��������", "52- ���� �����", "53- ���� �����", "54- ���� �����", "55- ���� ������",
			"56- ���� �������", "57- ���� ������", "58- ���� ��������", "59- ���� �����", "60- ���� ��������",
			"61- ���� ����", "62- ���� ������", "63- ���� ���������", "64- ���� �������", "65- ���� ������",
			"66- ���� �������", "67- ���� �����", "68- ���� �����", "69- ���� ������", "70- ���� �������",
			"71- ���� ���", "72- ���� ����", "73- ���� ������", "74- ���� ������", "75- ���� �������",
			"76- ���� �������", "77- ���� ��������", "78- ���� �����", "79- ���� ��������", "80- ���� ���",
			"81- ���� �������", "82- ���� ��������", "83- ���� ��������", "84- ���� ��������", "85- ���� ������",
			"86- ���� ������", "87- ���� ������", "88- ���� �������", "89- ���� �����", "90- ���� �����",
			"91- ���� �����", "92- ���� �����", "93- ���� �����", "94- ���� �����", "95- ���� �����", "96- ���� �����",
			"97- ���� �����", "98- ���� ������", "99- ���� �������", "100- ���� ��������", "101- ���� �������",
			"102- ���� �������", "103- ���� �����", "104- ���� ������", "105- ���� �����", "106- ���� ����",
			"107- ���� �������", "108- ���� ������", "109- ���� ��������", "110- ���� �����", "111- ���� �����",
			"112- ���� �������", "113- ���� �����", "114- ���� �����" };
	String ifs_reader[] = { "��� �������", "��� ������ ��� �����", "����� �� ���� �������", "���� ������",
			"����� ���� ������", "���� ���� ��������", "��� ����", "��� �������" };
	// SharedPreferences
	SharedPreferences prefs, prefs1;
	// Editor
	Editor edit_prefs;
	// int
	int reader_index, song_index;
	// String
	String MEDIA_PATH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playlist);
		setSpinner();
		setList();
		setReaderPrefs();
		

	}

	private void setSpinner() {
		prefs = getSharedPreferences("reader_file", MODE_PRIVATE);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(List_Activity.this,
				android.R.layout.simple_dropdown_item_1line, ifs_reader);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setSelectedNavigationItem(prefs.getInt("reader_key", 0));
		ActionBar.OnNavigationListener action = new ActionBar.OnNavigationListener() {

			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
				prefs = getSharedPreferences("reader_file", MODE_PRIVATE);
				edit_prefs = prefs.edit();
				edit_prefs.putInt("reader_key", itemPosition);
				edit_prefs.commit();
				
				reader_index = itemPosition;
				setReaderPath();
				return false;
			}
		};
		actionBar.setListNavigationCallbacks(adapter1, action);

	}

	private void setList() {
		ListView lst = new ListView(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(List_Activity.this,
				android.R.layout.simple_expandable_list_item_1, ifs_name);
		lst.setAdapter(adapter);
		setListAdapter(lst.getAdapter());

	}

	private void setReaderPrefs() {
		prefs = getSharedPreferences("reader_file", MODE_PRIVATE);
		edit_prefs = prefs.edit();
		reader_index = prefs.getInt("reader_key", 0);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		song_index = position;
		playSongAndOut();
	}

	private void playSongAndOut() {
		Intent i = new Intent(List_Activity.this , AndroidBuildingMusicPlayerActivity.class);
		i.putExtra("media_path", MEDIA_PATH);
		i.putExtra("song_name", ifs_name[song_index]);
		i.putExtra("reader_name", ifs_reader[reader_index]);
		finish();
		startActivity(i);
		
		
	}

	public String setPathAndPlay(int i, Context context) {
		String case_ = null;
		switch (i) {
		case 0:
			MEDIA_PATH = MEDIA_PATH + "/" + ifs_name[song_index] + ".mp3";
			File f1 = new File(MEDIA_PATH);
			if (f1.exists()) {
				prefs1 = getSharedPreferences("reader_file", MODE_PRIVATE);
				Editor edi = prefs1.edit();
				edi.putString("media_path", MEDIA_PATH);
				edi.putInt("song_index", song_index);
				edi.commit();
				case_ = MEDIA_PATH;
				Intent in = new Intent(getApplicationContext(), AndroidBuildingMusicPlayerActivity.class);
				setResult(100, in);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "���� ����� ������ ��������", 1500).show();
			}
			break;
		case 1:
			prefs = getSharedPreferences("reader_file", MODE_PRIVATE);
			reader_index = prefs.getInt("reader_key", 0);
			int song_prev = prefs.getInt("song_index", song_index);
			MEDIA_PATH = MEDIA_PATH + "/" + ifs_name[song_prev] + ".mp3";
			File f2 = new File(MEDIA_PATH);
			if (f2.exists()) {
				prefs1 = getSharedPreferences("reader_file", MODE_PRIVATE);
				Editor edi = prefs1.edit();
				edi.putString("media_path", MEDIA_PATH);
				edi.putInt("song_index", song_index);
				edi.commit();
				case_ = MEDIA_PATH;
			} else {
				Toast.makeText(getApplicationContext(), "���� ����� ������ ��������", 1500).show();
			}
			break;
		case 2:
			prefs = getSharedPreferences("reader_file", MODE_PRIVATE);
			reader_index = prefs.getInt("reader_key", 0);
			int song_next = prefs.getInt("song_index", song_index);
			MEDIA_PATH = MEDIA_PATH + "/" + ifs_name[song_next] + ".mp3";
			File f3 = new File(MEDIA_PATH);
			if (f3.exists()) {
				prefs1 = getSharedPreferences("raeder_file", MODE_PRIVATE);
				Editor edi = prefs1.edit();
				edi.putString("media_path", MEDIA_PATH);
				edi.putInt("song_index", song_index);
				edi.commit();
				case_ = MEDIA_PATH;
			} else {
				Toast.makeText(getApplicationContext(), "���� ����� ������ ��������", 1500).show();
			}
			break;
		case 4:
			prefs = context.getSharedPreferences("reader_file", 0);
			reader_index = prefs.getInt("reader_key", 0);
			setReaderPath();
			MEDIA_PATH = MEDIA_PATH + "/" + ifs_name[song_index] + ".mp3";
			File f4 = new File(MEDIA_PATH);
			if (f4.exists()) {
				prefs1 = getSharedPreferences("reader_file", MODE_PRIVATE);
				Editor edi = prefs1.edit();
				edi.putString("media_path", MEDIA_PATH);
				edi.putInt("song_index", song_index);
				edi.commit();
				case_ = MEDIA_PATH;
			} else {
				Toast.makeText(context, "���� ����� ������ ��������", 1500).show();
			}
			break;
		}
		return case_;

	}

	public void setReaderPath() {
		// TODO Auto-generated method stub
		switch (reader_index) {
		case 0:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/saad-el-ghamidi";
			break;
		case 1:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/abdel-basset";
			break;
		case 2:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/mishary-rashid-alafasy";
			break;
		case 3:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/saud-shuraim";
			break;
		case 4:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/mahmoud-khalil-al-hussary";
			break;
		case 5:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/mohamed-seddik-el-menchaoui";
			break;
		case 6:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/ali-jaber";
			break;
		case 7:
			MEDIA_PATH = "/mnt/sdcard/qurankarem/Souar/ali-al-hodhaifi";
			break;
		}
	}

	public void whenComplete() {

	}

	public void whenNext() {

	}

	public void whenPrevious() {

	}


	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), AndroidBuildingMusicPlayerActivity.class);
		setResult(99, i);
		finish();
		super.onBackPressed();
	}

}
