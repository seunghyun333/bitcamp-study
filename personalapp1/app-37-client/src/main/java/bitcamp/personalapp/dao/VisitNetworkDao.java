package bitcamp.personalapp.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import bitcamp.net.RequestEntity;
import bitcamp.net.ResponseEntity;
import bitcamp.personalapp.vo.Visit;


public class VisitNetworkDao implements VisitDao {

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public VisitNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Visit visit) {
    try {
      out.writeUTF(new RequestEntity().command(dataName + "/insert").data(visit).toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Visit> list() {
    try {
      out.writeUTF(new RequestEntity().command(dataName + "/list").toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        throw new RuntimeException(response.getResult());
      }

      return response.getList(Visit.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
