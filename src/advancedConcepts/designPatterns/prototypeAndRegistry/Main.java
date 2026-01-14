package advancedConcepts.designPatterns.prototypeAndRegistry;

public class Main {
    public static void main(String[] args) {
        StudentRegistry registry = new StudentRegistry();

        Student lldMWFBatch = new Student();
        lldMWFBatch.setBatchId(1);
        lldMWFBatch.setInstructorId(1);
        lldMWFBatch.setBatchAttendance(100);
        lldMWFBatch.setBatchPsp(90);
        lldMWFBatch.setBatchTiming("MWF");
        lldMWFBatch.setModuleName("LLD");

        registry.addStudentPrototype("LLD_MWF", lldMWFBatch);

        Student hldTTSBatch = new Student();
        hldTTSBatch.setBatchId(2);
        hldTTSBatch.setInstructorId(2);
        hldTTSBatch.setBatchAttendance(67);
        hldTTSBatch.setBatchPsp(40);
        hldTTSBatch.setBatchTiming("TTS");
        hldTTSBatch.setModuleName("HLD");

        registry.addStudentPrototype("HLD_TTS", hldTTSBatch);

        Student dsaMWFMorningBatch = new Student();
        dsaMWFMorningBatch.setBatchId(3);
        dsaMWFMorningBatch.setInstructorId(2);
        dsaMWFMorningBatch.setBatchAttendance(100);
        dsaMWFMorningBatch.setBatchPsp(95);
        dsaMWFMorningBatch.setBatchTiming("MWF Morning");
        dsaMWFMorningBatch.setModuleName("DSA");

        registry.addStudentPrototype("DSA_MWF_Morning", dsaMWFMorningBatch);




        //Taahaa from lldMWFBatch
        Student taahaa = lldMWFBatch.copy();
        taahaa.setId(20);
        taahaa.setName("Taahaa");
        taahaa.setPsp(90);
        taahaa.setContactDetails("Place A, City B - 1000001");

        //Rohit from lldMWFBatch
        Student rohit = registry.getStudentPrototype("LLD_MWF").copy(); // lldMWFBatch object == this inside copy method
        rohit.setId(10);
        rohit.setName("Rohit");
        rohit.setPsp(85);
        rohit.setContactDetails("Place A, City B - 1000001");

        //Achyut from hldTTSBatch
        Student achyut = registry.getStudentPrototype("HLD_TTS").copy();
        achyut.setId(30);
        achyut.setName("Achyut");
        achyut.setPsp(90);
        achyut.setContactDetails("Place A, City B - 1000001");

        //Satyam from DSA MWF morning
        Student satyam = registry.getStudentPrototype("DSA_MWF_Morning").copy();
        satyam.setId(40);
        satyam.setName("Satyam");
        satyam.setPsp(90);
        satyam.setContactDetails("Place A, City B - 1000001");
    }
}
