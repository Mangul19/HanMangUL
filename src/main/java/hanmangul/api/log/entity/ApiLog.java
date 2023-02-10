package hanmangul.api.log.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ApiLog implements Serializable {
    private Long logSeq;

    private String clientIp;

    private String clientNm;

    private String reqUri;

    private String method;

    private String queryString;

    private String reqBody;

    private String resBody;

    private LocalDateTime createdDate;
}
