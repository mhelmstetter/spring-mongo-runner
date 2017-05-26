package com.mongodb.load;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.data.annotation.Transient;

import com.mongodb.util.RandomUtils;

public class TestRecord {

    
    class FooList {
        
        private List<String> bright;
        private List<String> beautiful;
        private List<String> beer;
        private List<String> wine;
        private List<String> fruit;
        
        public FooList() {
            bright = new ArrayList<String>(1);
            beautiful = new ArrayList<String>(1);
            beer = new ArrayList<String>(1);
            wine = new ArrayList<String>(1);
            fruit = new ArrayList<String>(1);
            
            bright.add(rand.getRandomString(opts.textFieldLen));
            beautiful.add(rand.getRandomString(opts.textFieldLen));
            beer.add(rand.getRandomString(opts.textFieldLen));
            wine.add(rand.getRandomString(opts.textFieldLen));
            fruit.add(rand.getRandomString(opts.textFieldLen));
        }
        
        
    }

    
    private boolean academic;
    private boolean accept;
    private boolean acceptable;
    private boolean accepted;
    private boolean accepting;
    private boolean access;
    private boolean accidentally;
    private boolean accordingly;
    private boolean achieving;
    private boolean acquiring;
    private boolean afternoon;
    private boolean affecting;
    private boolean advising;
    private boolean advertising;
    private boolean advancing;
    private boolean administration;
    private boolean additional;
    private boolean arrangement;
    private boolean arithmetic;
    private int wonders;
    private int wooden;
    private int word;
    private int worded;
    
    private int words;
    private long work;
    private long worker;
    private long epoch;
    private List<FooList> worriedList;
    private List<FooList> wanderingList;
    
    
    private byte[] md5Hash;
    private long seqId;
    private long epochX;
    private long timestamp;
    private UUID xyzUuid;
    private UUID abcUuid;
    
    private String uuid;
    private String accepts;
    private String wording;
    private String world;
    
    @Transient
    private final static transient RandomUtils rand = new RandomUtils();
    
    @Transient
    private transient Random rng = new Random();
    
    @Transient
    private transient POCTestOptions opts;
    
    public TestRecord(POCTestOptions opts) {
        this.opts = opts;
        uuid = rand.createString(opts.textFieldLen);
        accepts = rand.createString(opts.textFieldLen);
        wording = rand.createString(opts.textFieldLen);
        world = rand.createString(opts.textFieldLen);
        xyzUuid = UUID.randomUUID();
        abcUuid = UUID.randomUUID();
        seqId = rng.nextLong();
        epochX = System.currentTimeMillis();
        timestamp = rng.nextLong();
        work = rng.nextLong();
        worker = rng.nextLong();
        epoch = rng.nextLong();
        wooden = rng.nextInt();
        wonders = rng.nextInt();
        word = rng.nextInt();
        worded = rng.nextInt();
        words = rng.nextInt();
        md5Hash = new byte[16];
        rng.nextBytes(md5Hash);
        
        worriedList = new ArrayList<FooList>(1);
        wanderingList = new ArrayList<FooList>(1);

        worriedList.add(new FooList());
        wanderingList.add(new FooList());
        
    }
    
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public boolean isAcademic() {
        return academic;
    }
    public void setAcademic(boolean academic) {
        this.academic = academic;
    }
    public boolean isAccept() {
        return accept;
    }
    public void setAccept(boolean accept) {
        this.accept = accept;
    }
    public boolean isAcceptable() {
        return acceptable;
    }
    public void setAcceptable(boolean acceptable) {
        this.acceptable = acceptable;
    }
    public boolean isAccepted() {
        return accepted;
    }
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    public boolean isAccepting() {
        return accepting;
    }
    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
    }
    public String getAccepts() {
        return accepts;
    }
    public void setAccepts(String accepts) {
        this.accepts = accepts;
    }
    public boolean isAccess() {
        return access;
    }
    public void setAccess(boolean access) {
        this.access = access;
    }
    public boolean isAccidentally() {
        return accidentally;
    }
    public void setAccidentally(boolean accidentally) {
        this.accidentally = accidentally;
    }
    public boolean isAccordingly() {
        return accordingly;
    }
    public void setAccordingly(boolean accordingly) {
        this.accordingly = accordingly;
    }
    public boolean isAchieving() {
        return achieving;
    }
    public void setAchieving(boolean achieving) {
        this.achieving = achieving;
    }
    public boolean isAcquiring() {
        return acquiring;
    }
    public void setAcquiring(boolean acquiring) {
        this.acquiring = acquiring;
    }
    public boolean isAfternoon() {
        return afternoon;
    }
    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }
    public boolean isAffecting() {
        return affecting;
    }
    public void setAffecting(boolean affecting) {
        this.affecting = affecting;
    }
    public boolean isAdvising() {
        return advising;
    }
    public void setAdvising(boolean advising) {
        this.advising = advising;
    }
    public boolean isAdvertising() {
        return advertising;
    }
    public void setAdvertising(boolean advertising) {
        this.advertising = advertising;
    }
    public boolean isAdvancing() {
        return advancing;
    }
    public void setAdvancing(boolean advancing) {
        this.advancing = advancing;
    }
    public boolean isAdministration() {
        return administration;
    }
    public void setAdministration(boolean administration) {
        this.administration = administration;
    }
    public boolean isAdditional() {
        return additional;
    }
    public void setAdditional(boolean additional) {
        this.additional = additional;
    }
    public boolean isArrangement() {
        return arrangement;
    }
    public void setArrangement(boolean arrangement) {
        this.arrangement = arrangement;
    }
    public boolean isArithmetic() {
        return arithmetic;
    }
    public void setArithmetic(boolean arithmetic) {
        this.arithmetic = arithmetic;
    }
    public int getWonders() {
        return wonders;
    }
    public void setWonders(int wonders) {
        this.wonders = wonders;
    }
    public int getWooden() {
        return wooden;
    }
    public void setWooden(int wooden) {
        this.wooden = wooden;
    }
    public int getWord() {
        return word;
    }
    public void setWord(int word) {
        this.word = word;
    }
    public int getWorded() {
        return worded;
    }
    public void setWorded(int worded) {
        this.worded = worded;
    }
    public String getWording() {
        return wording;
    }
    public void setWording(String wording) {
        this.wording = wording;
    }
    public int getWords() {
        return words;
    }
    public void setWords(int words) {
        this.words = words;
    }
    public long getWork() {
        return work;
    }
    public void setWork(long work) {
        this.work = work;
    }
    public long getWorker() {
        return worker;
    }
    public void setWorker(long worker) {
        this.worker = worker;
    }
    public long getEpoch() {
        return epoch;
    }
    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }
   
    public String getWorld() {
        return world;
    }
    public void setWorld(String world) {
        this.world = world;
    }
    public byte[] getMd5Hash() {
        return md5Hash;
    }
    public void setMd5Hash(byte[] md5Hash) {
        this.md5Hash = md5Hash;
    }
    public long getSeqId() {
        return seqId;
    }
    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }
    public long getEpochX() {
        return epochX;
    }
    public void setEpochX(long epochX) {
        this.epochX = epochX;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public UUID getXyzUuid() {
        return xyzUuid;
    }
    public void setXyzUuid(UUID xyzUuid) {
        this.xyzUuid = xyzUuid;
    }
    public UUID getAbcUuid() {
        return abcUuid;
    }
    public void setAbcUuid(UUID abcUuid) {
        this.abcUuid = abcUuid;
    }


    


}
