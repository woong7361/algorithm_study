// https://school.programmers.co.kr/learn/courses/30/lessons/17683
// 52 start


import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        // 자신이 기억한 멜로디를 통해 음악을 찾는다.
        // 멜로디는 음악 끝부분과 처음 부분이 이어서 재생된 부분일 수 있다.
        // 한 음악을 중간에 끊은 경우 원본 음악네는 네오가 기억한 멜로디가 들어있다 해도 네오가 들은 곡이 아닐 수 있다.
        // 따라서 재생시간과 제공된 악보를 직접 보면서 비교하려 한다.
        // 악보의 음은 12가지이다.
        // 음악이 00:00 을 넘겨서 재생되는 일은 없다.
        // 조건이 ㅇ일치하는 음악이 여러개일경우 라디오에서 재생된 기간이 제일 긴 음악 제목을 반환한다. 그것도 같을 경우 먼저 입력된 음악을 반환한다.
        // 음은 1분에 1개씩 재생된다.

        // m 네오가 기억한 멜로디를 담은 문자열 ex. "CC#BCC#BCC#BCC#B"
        // musicinfos 방송된 곡의 정보를 담고있는 배열 ex. ["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"]

        // 해결 방법
        // 1. 멜로디와 일치하는 곡 찾기
        // 2. 여러개가 있다면 해당 곡들중 가장 오래 재생된 곡 찾기 -> 제일 먼저 입력된 음악 찾기

        // 자신의 멜로디 list로 나누기
        // Map 에 song 등록 (melody List, runningtime, index)
        // music과 일치하는지 확인 (시간을 보면서)
        // 일치하는 곡들이 여러개라면 sorting을 통해 first를 뽑아 해결

        List<String> listenMelody = stringMelodyToList(m);

        ArrayList<Song> result = new ArrayList<>();
        ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] split = musicinfos[i].split(",");

            songs.add(new Song(i, getTime(split[1], split[0]), split[2], stringMelodyToList(split[3])));
        }

        for (Song song : songs) {
            for (int i = 0; i < song.melody.size(); i++) {
                // 같다면 노트 진행
                int time = 0;
                while (time < listenMelody.size() && time+i <= song.runningTime) {
                    if (! listenMelody.get(time).equals(song.melody.get((time+i) % song.melody.size()))) {
                        break;
                    }
                    time++;
                }

                if (time == listenMelody.size()) {
                    result.add(song);
                    break;
                }

            }
        }

        // 기본 오름차순
        result.sort((song, t1) -> {
            if (song.runningTime != t1.runningTime) {
                return - (song.runningTime - t1.runningTime);
            } else {
                return song.index - t1.index;
            }
        });

        if (result.isEmpty()) {
            return "(None)";
        } else {
            return result.get(0).name;
        }
    }

    private int getTime(String t1, String t2) {
        String[] t1Split = t1.split(":");
        String[] t2Split = t2.split(":");

        return (Integer.parseInt(t1Split[0]) * 60 + Integer.parseInt(t1Split[1])) -
                (Integer.parseInt(t2Split[0]) * 60 + Integer.parseInt(t2Split[1]));
    }

    private List<String> stringMelodyToList(String m) {
        ArrayList<String> result = new ArrayList<>();

        StringBuilder em = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            em.append(m.charAt(i));

            // next가 #이면 pass
            // next가 알파벳이면 음 완료
            // 단 next가 존재할때
            if (i == m.length() - 1) {
                result.add(em.toString());
            } else if (m.charAt(i + 1) != '#') {
                result.add(em.toString());
                em = new StringBuilder();
            }

        }

        return result;
    }

    private static class Song {
        private int index;

        private int runningTime;
        private String name;
        private List<String> melody;

        public Song(int index, int runningTime, String name, List<String> melody) {
            this.index = index;
            this.runningTime = runningTime;
            this.name = name;
            this.melody = melody;
        }

    }
}
