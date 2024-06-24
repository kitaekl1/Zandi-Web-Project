package com.assey.zandi.bookmark;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cf_likelist")
public class LikelistVO {

    @EmbeddedId
    private LikelistId id;

    // Getter and Setter
    public LikelistId getId() {
        return id;
    }

    public void setId(LikelistId id) {
        this.id = id;
    }

    @Embeddable
    public static class LikelistId implements Serializable {
        @Column(name = "memId") // 매핑할 데이터베이스 필드명 지정
        private String memId;
        
        @Column(name = "prCode") // 매핑할 데이터베이스 필드명 지정
        private int prCode;

        // Getters and Setters
        public String getMemId() {
            return memId;
        }

        public void setMemId(String memId) {
            this.memId = memId;
        }

        public int getPrCode() {
            return prCode;
        }

        public void setPrCode(int prCode) {
            this.prCode = prCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LikelistId that = (LikelistId) o;
            return prCode == that.prCode && Objects.equals(memId, that.memId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(memId, prCode);
        }
    }
}