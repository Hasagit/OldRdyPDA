package com.qs.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class FileUtils {
	public static String getPath(Context context, Uri uri) { 
		String path=null;
		if ("content".equalsIgnoreCase(uri.getScheme())) { 
			String[] projection = { "_data" }; 
			Cursor cursor = null; 

			try { 
				cursor = context.getContentResolver().query(uri, projection,null, null, null); 
				int column_index = cursor.getColumnIndexOrThrow("_data"); 
				if (cursor.moveToFirst()) { 
					path = cursor.getString(column_index); 
					cursor.close();
					return path;
				} 
			} catch (Exception e) { 
				// Eat it 
			} 
		} 

		else if ("file".equalsIgnoreCase(uri.getScheme())) { 
			return uri.getPath(); 
		} 
		return null; 
	} 

}
