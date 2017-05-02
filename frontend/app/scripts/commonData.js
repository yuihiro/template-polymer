'use strict';
class CommonData {
    constructor() {
        this.crypto_key = "ANYCLICK-KO-100001";
        // 사용자구분 목록
        this.user_type_lst = [];
        // 사용자그룹 목록
        this.dept_lst = [];
        // 사용자그룹 트리 목록
        this.dept_tree_lst = [];
        // NAS그룹 목록
        this.nas_group_lst = [];
        this.active_lst = [{
            label: "접속중",
            value: 1
        }, {
            label: "미접속",
            value: 0
        }];
        // 사용자레벨 목록
        this.user_level_lst = [{
            label: "일반사용자",
            value: "2"
        }, {
            label: "서브관리자",
            value: "1"
        }, {
            label: "최고관리자",
            value: "2"
        }];
        // 사용자상태 목록
        this.user_status_lst = [{
            label: "정상",
            value: "A"
        }, {
            label: "정지",
            value: "D"
        }, {
            label: "승인대기",
            value: "W"
        }, {
            label: "정지승인대기",
            value: "W"
        }, {
            label: "일시정지",
            value: "H"
        }, {
            label: "유효기간초과",
            value: "I"
        }, {
            label: "장기미사용",
            value: "U"
        }, {
            label: "잠김",
            value: "L"
        }];
        // EAP알고리즘 목록
        this.eap_algo_lst = [{
            label: "기본",
            value: 0
        }, {
            label: "EAP_MD5",
            value: 4
        }, {
            label: "EAP-GTC",
            value: 6
        }, {
            label: "EAP-TLS",
            value: 13
        }, {
            label: "EAP_LEAP",
            value: 17
        }, {
            label: "EAP_TTLS",
            value: 21
        }, {
            label: "EAP_PEAP",
            value: 25
        }, {
            label: "EAP_MSCHAPV2",
            value: 26
        }];
        // 사용자 승인대기 타입
        this.user_request_type_lst = [{
            label: "등록",
            value: "UA"
        }, {
            label: "정지",
            value: "UD"
        }, {
            label: "탈퇴",
            value: "UQ"
        }, {
            label: "기간연장",
            value: "DE"
        }];
        // 관리자로그 작업 타입
        this.log_action_lst = [{
            label: "DHCPV6",
            value: "K"
        }, {
            label: "단말현황",
            value: "J"
        }, {
            label: "ACL",
            value: "A"
        }, {
            label: "다운로드",
            value: "B"
        }, {
            label: "파일무결성",
            value: "C"
        }, {
            label: "DHCP",
            value: "D"
        }, {
            label: "컨트롤러",
            value: "E"
        }, {
            label: "IP그룹",
            value: "F"
        }, {
            label: "Agent",
            value: "G"
        }, {
            label: "부서",
            value: "H"
        }, {
            label: "IP",
            value: "I"
        }, {
            label: "관리자",
            value: "M"
        }, {
            label: "NAC",
            value: "N"
        }, {
            label: "NAS그룹",
            value: "O"
        }, {
            label: "승인",
            value: "P"
        }, {
            label: "승인요청",
            value: "R"
        }, {
            label: "NAS",
            value: "S"
        }, {
            label: "기타",
            value: "T"
        }, {
            label: "계정",
            value: "U"
        }, {
            label: "관리",
            value: "Z"
        }];
        //자동로그아웃 시간
        this.logout_type_lst = [{
            label: "사용안함",
            value: 1
        }, {
            label: "10분",
            value: 2
        }, {
            label: "20분",
            value: 3
        }, {
            label: "30분",
            value: 4
        }]
        this.time_lst = [];
        _.times(24, (item) => {
            this.time_lst.push({
                label: item + '시',
                value: _.padStart(item + "", 2, '0')
            });
        });
        //자동로그아웃 시간
        this.log_level_lst = [{
            label: "ERROR",
            value: 3
        }, {
            label: "TRACE",
            value: 7
        }, {
            label: "DEBUG",
            value: 15
        }];
        //자동로그아웃 시간
        this.log_target_lst = [{
            label: "인증성공",
            value: 3
        }, {
            label: "인증실패",
            value: 7
        }, {
            label: "인증성공/실패",
            value: 15
        }];
    }
}