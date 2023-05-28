# 일하잡 _ 구인구직 웹 프로젝트
실제 구인구직 사이트 잡코리아를 모티브한 프로젝트

## Description
프로젝트 기간 2023.01.18 ~ 2023.02.15 \
본 프로젝트는 실제 운영 되고 있는 쇼핑몰들의 데이터를 가져와 운영자가 한 눈에 볼 수 있게 끔 DashBorad를 보여줍니다.
-   기획 동기 및 의도
    -   데이터 분석 환경 구축의 필요성 → 데이터 분석을 통한 매출 증대 목표
        -   분산되어있는 데이터
        -   주요 지표 파악의 어려움
        -   고객 데이터 수집 불가
    -   데이터 파이프라인 구축
        -   분산되어있는 데이터를 하나의 DB에 적재한 뒤, 한 테이블에 플랫폼 별 주문 데이터 통합 

## Service INFO
> 실제 기업의 각 플랫폼 별 데이터를 수집 및 분석하여 사업자가 활용할 수 있는 대시보드 제작 및 배포
- 데이터 
	- H-log 쇼핑몰의 각 플랫폼 별 데이터(cafe24, 스마트스토어, 에이블리)
	- 사용자의 로그분석을 위해 GA를 연동한 데이터
- USE TECH
	- Python, Django, Selenium, AWS, Tableau, Chart.js, Airflow

- 페이지 구현 영상



https://user-images.githubusercontent.com/98085184/230868655-d57ac5aa-4f2f-412e-abd7-accd443fceb0.mp4



## PIPELINE
![donone_pipeling](https://user-images.githubusercontent.com/98085184/230866641-feae6ecb-80ea-4509-8bea-e99c7b0466b2.png)

> Airflow 오케스트라 환경에서 API를 개발해 크롤링 진행 후 데이터 전처리 이후 RDS에 저장 해주었습니다.
## Environment

> Python Version 3.8 \
> Docker Mysql 8.1 \
> Django 3.2.3 \
> Airflow 2.3.1

## Troubleshooting
- 원래는 사이트에 접속해 버튼을 눌러 쇼핑몰 데이터를 최신화 시켜주는 방안으로 잡았는데
User가 20명이 동시 접속 해서 동시에 버튼을 눌러 보니 몇명의 사용자가 크롤링이 튕기는 현상이 발생했다.
- 하나의 토큰 값으로 크롤링으로 동시에 20개가 돌아가다 보니 크롤링 서버에서 오류가 발생한 것 같았다.
	- 차라리 Airflow를 사용해 데이터는 매일 아침 6시에 최신화 시켜주어 안정화가 되었다.


## RETROSPECTIVE
저희 팀원 중 한 명이 운영하는 온라인 쇼핑몰 데이터를 정제하여 ETL 서비스를 구축해 DashBorad 구현,
실제로 매출까지 올리는 프로젝트를 실시하였습니다.

제가 맡은 역할은 데이터를 API & 크롤링으로 받아와 DB에 적재 후, Django 백단 구현, 배포까지 하는 일을 맡았었습니다.

그리고 DB를 적재하는 과정에서 DA가 원하는 데이터와 Front-end가 원하는 데이터의 종류가 달랐습니다.
코로나 시기의 특성상 저희는 온라인으로 이야기를 하였고, 저는 그들이 따로따로 원하는 데이터에 맞춰 몇 번의 수정을 거쳤습니다.

그리고 데이터가 바뀔 때마다 그들은 불만을 표출하였습니다.

저는 소통이 필요하다 느껴 오프라인으로 불러모아 서로 원하는 데이터를 맞추고, 그에 따라 데이터를 정제하였습니다.
그 결과, 모두의 불만 없어졌고 이후로 오프라인 모임 만을 지속적으로 가지게 되었습니다.

오프라인 모임을 함으로써 소통이 원활하게 되었고, 저희는 아무런 트러블 없이 프로젝트가 순항하여 좋은 결과로 끝나게 되었습니다.

또한 이 프로젝트를 진행하던 당시 데이터를 가져와 DashBorad로 기간 별 최신화를 해주어야 하는데 처음에는 버튼 형식으로 사용자에게 제공해주었습니다.
사용자가 사이트에 접근해 버튼을 눌러야지만 데이터를 가져오는 방식이었습니다.

하지만 그러한 방식은 UX 적으로도 불편하고, 중간에 크롤링이 들어가 있다 보니 데이터를 가져오는 것도 시간이 걸렸습니다.
그래서 저는 차라리 사용자에게 버튼 형식의 데이터 가져오기가 아닌 Airflow를 도입해 아침 6시에 자동으로 데이터를 가져오게 하는 flow를 작업하였습니다.

그 결과, 매일 아침 6시마다 DashBorad를 최신화 시켜 주었고, 사용자의 입장에서도 데이터 Load를 기다릴 필요 없이 빠르게 확인할 수 있게 되었습니다.