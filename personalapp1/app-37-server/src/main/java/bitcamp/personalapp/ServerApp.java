package bitcamp.personalapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import bitcamp.net.RequestEntity;
import bitcamp.net.ResponseEntity;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.BoardListDao;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.DiaryListDao;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.dao.VisitListDao;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Diary;
import bitcamp.personalapp.vo.Visit;

public class ServerApp {

  int port;
  ServerSocket serverSocket;

  DiaryDao diaryDao = new DiaryListDao("diary.json");
  BoardDao boardDao = new BoardListDao("board.json");
  VisitDao visitDao = new VisitListDao("visit.json");



  public ServerApp(int port) throws Exception {
    this.port = port;
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... bitcamp.myapp.ServerApp 포트번호");
      return;
    }

    ServerApp app = new ServerApp(Integer.parseInt(args[0]));
    app.execute();
    app.close();
  }


  public void execute() throws Exception {
    System.out.println("[MyDiary 서버 애플리케이션]");

    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    while (true) {
      RequestEntity request = RequestEntity.fromJson(in.readUTF());

      String command = request.getCommand();
      System.out.println(command);


      if (command.equals("quit")) {
        break;
      }
      ResponseEntity response = new ResponseEntity();

      switch (command) {
        case "board/list":
          response.status(ResponseEntity.SUCCESS).result(boardDao.list());
          break;
        case "board/insert":
          boardDao.insert(request.getObject(Board.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "board/findBy":
          Board board = boardDao.findBy(request.getObject(Integer.class));
          if (board == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시글이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(board);
          }
          break;
        case "board/update":
          int value = boardDao.update(request.getObject(Board.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "board/delete":
          value = boardDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;

        case "diary/list":
          response.status(ResponseEntity.SUCCESS).result(diaryDao.list());
          break;
        case "diary/insert":
          diaryDao.insert(request.getObject(Diary.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "diary/findBy":
          Diary diary = diaryDao.findBy(request.getObject(Integer.class));
          if (diary == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시글이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(diary);
          }
          break;
        case "diary/update":
          value = diaryDao.update(request.getObject(Diary.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "diary/delete":
          value = diaryDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;

        case "visit/list":
          response.status(ResponseEntity.SUCCESS).result(visitDao.list());
          break;
        case "visit/insert":
          visitDao.insert(request.getObject(Visit.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        default:
          response.status(ResponseEntity.ERROR).result("해당 명령을 지원하지 않습니다!");
      }

      out.writeUTF(response.toJson());
    }

    in.close();
    out.close();
    socket.close();

  }
}

