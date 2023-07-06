package bitcamp.personalapp.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import bitcamp.personalapp.net.RequestEntity;
import bitcamp.personalapp.net.ResponseEntity;
import bitcamp.personalapp.vo.Diary;

public class DiaryNetworkDao implements DiaryDao{
	
	String dataName;
	DataInputStream in;
	DataOutputStream out;
	
	public DiaryNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
		this.dataName = dataName;
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void insert(Diary diary) {
		try {
			out.writeUTF(new RequestEntity()
				.command(dataName + "/insert")
				.data(diary)
				.toJson());
			
			ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
			if (response.getStatus().equals(ResponseEntity.ERROR)) {
				throw new RuntimeException(response.getResult());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Diary> list() {
		try {
			out.writeUTF(new RequestEntity()
				.command(dataName + "/list")
				.toJson());
			
			ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
			if (response.getStatus().equals(ResponseEntity.FAILURE)) {
				throw new RuntimeException(response.getResult());
			}
			
			return response.getList(Diary.class);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Diary findBy(int no) {
		try {
			out.writeUTF(new RequestEntity()
				.command(dataName + "/findBy")
				.data(no)
				.toJson());
			
			ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
			
			if (response.getStatus().equals(ResponseEntity.ERROR)) {
				throw new RuntimeException(response.getResult());
			} else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
				return null;
			}
			return response.getObject(Diary.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	 @Override
	public int update(Diary diary) {
		try {
			out.writeUTF(new RequestEntity()
			   .command(dataName + "/update")
			   .data(diary)
			   .toJson());
			
			ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
			
			if (response.getStatus().equals(ResponseEntity.ERROR)) {
				throw new RuntimeException(response.getResult());
			} else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
				return 0;
			}
			
			return response.getObject(Integer.class);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	  @Override
	  public int delete(int no) {
	    try {
	      out.writeUTF(new RequestEntity()
	          .command(dataName + "/delete")
	          .data(no)
	          .toJson());

	      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

	      if (response.getStatus().equals(ResponseEntity.ERROR)) {
	        throw new RuntimeException(response.getResult());
	      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
	        return 0;
	      }

	      return response.getObject(Integer.class);

	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }
	

}
