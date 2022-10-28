package com.zezoo.qurankarem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import com.zezoo.qurankarem.SongsManager.FileExtensionFilter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SouarList extends ListActivity {
	String ifs_song[] = { "���� �������", "���� ������", "���� �� �����",
			"���� ������", "���� �������", "���� �������", "���� �������",
			"���� �������", "���� ������", "���� ����", "���� ���",
			"���� ����", "���� �����", "���� �������", "���� �����",
			"���� �����", "���� �������", "���� �����", "���� ����", "���� ��",
			"���� ��������", "���� ����", "���� ��������", "���� �����",
			"���� �������", "���� �������", "���� �����", "���� �����",
			"���� ��������", "���� �����", "���� �����", "���� ������",
			"���� �������", "���� ���", "���� ����", "���� ��", "���� �������",
			"���� �", "���� �����", "���� ����", "���� ����", "���� ������",
			"���� ������", "���� ������", "���� �������", "���� �������",
			"���� ����", "���� �����", "���� �������", "���� �",
			"���� ��������", "���� �����", "���� �����", "���� �����",
			"���� ������", "���� �������", "���� ������", "���� ��������",
			"���� �����", "���� ��������", "���� ����", "���� ������",
			"���� ���������", "���� �������", "���� ������", "���� �������",
			"���� �����", "���� �����", "���� ������", "���� �������",
			"���� ���", "���� ����", "���� ������", "���� ������",
			"���� �������", "���� �������", "���� ��������", "���� �����",
			"���� ��������", "���� ���", "���� �������", "���� ��������",
			"���� ��������", "���� ��������", "���� ������", "���� ������",
			"���� ������", "���� �������", "���� �����", "���� �����",
			"���� �����", "���� �����", "���� �����", "���� �����",
			"���� �����", "���� �����", "���� �����", "���� ������",
			"���� �������", "���� ��������", "���� �������", "���� �������",
			"���� �����", "���� ������", "���� �����", "���� ����",
			"���� �������", "���� ������", "���� ��������", "���� �����",
			"���� �����", "���� �������", "���� �����", "���� �����" };
	String MEDIA_PATH;
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	File home, Root, Dir;
	int readerIndex;
	int songIndex = 0;
	String path = "";
	ArrayAdapter<String> adapter;

	SouarList(int reader_now) {
		reader_now = readerIndex;
		switch (reader_now) {
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

	public ArrayList<HashMap<String, String>> getPlayList() {

		// return songs list array
		return songsList;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(SouarList.this,
				R.layout.playlist_item, ifs_song);
		setListAdapter(adapter);
		for (int i = 0; i == 114; i++) {
			adapter.add(ifs_song[i]);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		songIndex = position;
		File file = new File(MEDIA_PATH + "/" + ifs_song[position] + ".mp3");
		if (file.exists()) {
			Intent in = new Intent(getApplicationContext(),
					AndroidBuildingMusicPlayerActivity.class);
			// Sending songIndex to PlayerActivity
			path = file.getPath();
			in.putExtra("songIndex", songIndex);
			in.putExtra("songPath", path);
			in.putExtra("readerIndex", readerIndex);
			setResult(100, in);
			// Closing PlayListView
			finish();
		} else {
			Toast.makeText(getApplicationContext(), "��� ���� ����� ������",
					2000).show();
		}

	}

	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}

}
