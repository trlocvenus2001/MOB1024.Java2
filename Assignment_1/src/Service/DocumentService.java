package Service;

import java.util.List;

import Entity.Copy;
import Entity.Document;
import Enum.Status;

public interface DocumentService {
	void addDocument(Document document) throws Exception;

	void updateDocument(Document document) throws Exception;

	void deleteDocument(String documentId) throws Exception;

	Document findIdDocument(String id);

	List<Document> getAllDocuments();

	void addCopyDocument(String documentId, Copy copy) throws Exception;

	void updateCopyDocument(String documentId, String copyId, Status newStt) throws Exception;

	void deleteCopyDocument(String documentId, String copyId) throws Exception;

	List<Copy> getCopyIdDocument(String documentId) throws Exception;

	void saveFile(String filePath) throws Exception;

	void loadFile(String filePath) throws Exception;
}