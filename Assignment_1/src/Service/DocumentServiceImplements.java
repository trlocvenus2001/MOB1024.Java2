package Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Entity.Copy;
import Entity.Document;
import Enum.Status;
import Exception.DuplicateIdException;

public class DocumentServiceImplements implements DocumentService {
	private List<Document> listDocument = new ArrayList<>();

	@Override
	public void addDocument(Document document) throws Exception {
		// TODO Auto-generated method stub
		if (findIdDocument(document.getId()) != null) {
			throw new DuplicateIdException("Lỗi: ID Document ' " + document.getId() + " 'đã tồn tại");
		}
		listDocument.add(document);
	}

	@Override
	public void updateDocument(Document document) throws Exception {
		// TODO Auto-generated method stub
		Document existDoc = findIdDocument(document.getId());
		if (existDoc == null) {
			throw new Exception(">> Không tìm thấy tài liệu cần sửa!");
		}
		existDoc.setTitle(document.getTitle());
		existDoc.setAuthor(document.getAuthor());
		existDoc.setCategory(document.getCategory());
	}

	@Override
	public void deleteDocument(String documentId) throws Exception {
		Document doc = findIdDocument(documentId);
		if (doc == null) {
			throw new Exception(">> Không tìm thấy tài liệu để xóa!");
		}
		listDocument.remove(doc);
	}

	@Override
	public Document findIdDocument(String id) {
		// TODO Auto-generated method stub
		for (Document doc : listDocument) {
			if (doc.getId().equals(id)) {
				return doc;
			}
		}
		return null;
	}

	@Override
	public List<Document> getAllDocuments() {
		// TODO Auto-generated method stub
		return listDocument;
	}

	@Override
	public void addCopyDocument(String documentId, Copy copy) throws Exception {
		// TODO Auto-generated method stub
		Document doc = findIdDocument(documentId);
		if (doc == null) {
			throw new Exception(">> Không tìm thấy tài liệu gốc để thêm bản sao!");
		}
		doc.addCopy(copy);

	}

	@Override
	public void updateCopyDocument(String documentId, String copyId, Status newStt) throws Exception {
		List<Copy> listCopy = getCopyIdDocument(documentId);
		for (Copy copy : listCopy) {
			if (copy.getId().equals(copyId)) {
				copy.setStt(newStt);
				return;
			}
		}
		throw new Exception(">> Không tìm thấy mã bản sao này!");
	}

	@Override
	public void deleteCopyDocument(String documentId, String copyId) throws Exception {
		List<Copy> copies = getCopyIdDocument(documentId);
		for (int i = 0; i < copies.size(); i++) {
			if (copies.get(i).getId().equals(copyId)) {
				copies.remove(i);
				return;
			}
		}
		throw new Exception(">> Không tìm thấy bản sao để xóa!");
	}

	@Override
	public List<Copy> getCopyIdDocument(String documentId) throws Exception {
		// TODO Auto-generated method stub
		Document doc = findIdDocument(documentId);
		if (doc == null) {
			throw new Exception("Không tìm thấy tài liệu!");
		}
		return doc.getListCopy();
	}

	@Override
	public void saveFile(String file) throws Exception {
		// TODO Auto-generated method stub
		try (ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(listDocument);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadFile(String file) throws Exception {
		java.io.File f = new java.io.File(file);
		if (!f.exists())
			return;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			listDocument = (List<Document>) ois.readObject();
		}
	}
}
