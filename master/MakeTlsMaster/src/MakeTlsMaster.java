import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MakeTlsMaster{
	static final int MASTER_LIVE = 1;
    static final int MASTER_LIVEHOUSE = 2;

   public static void main(String[]args){
	   MakeTlsMaster mast = new MakeTlsMaster();

	   try {
		   String tempMaster = "tempmaster.bin";
		   FileInputStream fin = new FileInputStream(tempMaster);
		   DataInputStream in = new DataInputStream(fin);

		   FileOutputStream fout = new FileOutputStream("master.bin");
		   DataOutputStream out = new DataOutputStream(fout);

		   //マスターバージョン
		   int version = mast.putInt32(in, out);
		   
		   //プログラムバージョン
		   mast.putInt32(in, out);

		   int masterCount = mast.putInt16(in, out);

		   for( int i = 0;i < masterCount;i++ )
		   {
			   int masterType = mast.putInt16(in, out);

			   switch(masterType)
			   {
                case MASTER_LIVE:
				   mast.outputLiveInfoMast(in, out);
				   break;
                case MASTER_LIVEHOUSE:
                	mast.outputLiveHouseMast(in, out);
                	break;
			   default:
				   break;
			   }
		   }

		   out.close();
		   fout.close();
		   in.close();
		   fin.close();

		   File file = new File(String.valueOf(version));
		   if(file.delete()){
			   //ファイル削除成功
			   System.out.println(tempMaster+"削除。");
		   }

		   File newdir = new File(String.valueOf(version));
		   if(newdir.mkdir())
		   {
			   System.out.println(newdir.getName() + "フォルダ作成しました。");
		   }
		   String dirPath = newdir.getAbsolutePath();

		   file = new File("version.bin");
		   if(file.renameTo(new File(dirPath, file.getName())))
		   {
			   System.out.println(newdir.getName() + "に" + file.getName() + "を移動しました。");
		   }

		   file  = new File("master.bin");
		   if(file.renameTo(new File(dirPath, file.getName())))
		   {
			   System.out.println(newdir.getName() + "に" + file.getName() + "を移動しました。");
		   }

		   file = new File(String.valueOf(version) + "\\" + String.valueOf(version));
		   if(file.createNewFile())
		   {
			   System.out.println(newdir.getName() + "に" + file.getName() + "を移動しました。");
		   }

		   file = new File(tempMaster);
		   if(file.delete()){
			   //ファイル削除成功
			   System.out.println(tempMaster+"削除。");
		   }

		   System.out.println("masterファイル変換完了。");
	   } catch (Exception e) {
		   e.printStackTrace();
		   System.out.println("変換中にエラーが発生しました。");
	   }
	   //readFile("bin/master.bin");

   }

   private void outputLiveInfoMast(DataInputStream in, DataOutputStream out)
   {
	   try{
		   int masterCount = this.putInt16(in, out); 	//masterCount

		   for(int i = 0;i < masterCount;i++ )
		   {
			   this.putInt16(in, out);
			   this.putInt32(in, out);
			   this.putInt16(in, out);
			   this.putString16(in, out);
			   this.putString16(in, out);
			   this.putString16(in, out);
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
		   System.out.println("変換中にエラーが発生しました。");
	   }
   }
    
    private void outputLiveHouseMast(DataInputStream in, DataOutputStream out)
    {
        try{
            int masterCount = this.putInt16(in, out); 	//masterCount
            
            for(int i = 0;i < masterCount;i++ )
            {
                this.putInt16(in, out);
                this.putString16(in, out);
                this.putString16(in, out);
                this.putInt16(in, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("変換中にエラーが発生しました。");
        }
    }
   
   private short putInt16(DataInputStream in, DataOutputStream out)
   {
	   byte[] byteData = new byte[2];
	   try {
		   in.read(byteData, 0, 2);
		   ByteBuffer buffer = ByteBuffer.wrap(byteData);
		   buffer.order(ByteOrder.LITTLE_ENDIAN);

		   short number = buffer.getShort();
		   out.writeShort(number);

		   return number;
	   } catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("変換中にエラーが発生しました。");
	   }

	   System.out.println("■■error putInt16");
	   return 0;
   }

   private float putFloat(DataInputStream in, DataOutputStream out)
   {
	   byte[] byteData = new byte[4];
	   try {
		   in.read(byteData, 0, 4);
		   ByteBuffer buffer = ByteBuffer.wrap(byteData);
//		   buffer.order(ByteOrder.LITTLE_ENDIAN);

		   float number = buffer.getFloat();
		   out.writeFloat(number);

		   return number;
	   } catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("変換中にエラーが発生しました。");
	   }

	   System.out.println("■■error putFloat");
	   return 0;
   }

   private int putInt32(DataInputStream in, DataOutputStream out)
   {
	   byte[] byteData = new byte[4];
	   try {
		   in.read(byteData, 0, 4);
		   ByteBuffer buffer = ByteBuffer.wrap(byteData);
		   buffer.order(ByteOrder.LITTLE_ENDIAN);

		   int number = buffer.getInt();
		   out.writeInt(number);

		   return number;
	   } catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("変換中にエラーが発生しました。");
	   }

	   System.out.println("■■error putInt32");
	   return 0;
   }

   private void putString16(DataInputStream in, DataOutputStream out)
   {
	   byte[] byteData = new byte[2];
	   try {
		   in.read(byteData, 0, 2);
		   ByteBuffer buffer = ByteBuffer.wrap(byteData);
		   buffer.order(ByteOrder.LITTLE_ENDIAN);

		   short number = buffer.getShort();

		   //文字列の取得
		   byteData = new byte[number];
		   in.read(byteData, 0, number);

		   String str = new String(byteData, "UTF8");

		   byteData = str.getBytes("UTF8");

		   if( number > 0)
		   {
			   System.out.println(str);
		   }

		   out.writeShort(byteData.length);
		   out.write(byteData);

	   } catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("変換中にエラーが発生しました。");
	   }
   }
}