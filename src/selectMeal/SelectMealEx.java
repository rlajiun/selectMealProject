package selectMeal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import selectMeal.dto.Restaurant;
import selectMeal.service.BooService;
import selectMeal.service.MemService;
import selectMeal.service.MenuService;
import selectMeal.service.ResMenuService;
import selectMeal.service.ResService;

public class SelectMealEx {
	static Scanner scanner = new Scanner(System.in);
	static String name = null, id = null, pw = null;

	public static void main(String[] args) {
		MemService memService = new MemService();

		int sel = 0;

		while (sel != 3) {
			try {
				System.out.println("=================================================");
				System.out.println("  1. 로그인    |  2. 회원가입    |  3. 종료    |  4. 회원 출력     ");
				System.out.println("=================================================");
				System.out.print("번호 입력>>> ");
				sel = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("다시 입력하여 주십시오.");
				continue;
			}
			if (sel == 1) {
				inputLogin();
				name = memService.login(id, pw);
				if (name == null) {
					System.out.println("일치하는 정보가 없습니다.");
				} else {
					System.out.println(name + "님 로그인 되었습니다.");
					searchMenu();
				}
			} else if (sel == 2) {
				inputRegister();
				memService.register(name, id, pw);
			} else if (sel == 3) {
				System.out.println("이용해주셔서 감사합니다.");
				scanner.close();
				System.exit(0);
			} else if (sel == 4) {
				memService.getMemList();
			} else {
				System.out.println("다시 입력하여 주십시오.");
			}
		}

	}

	private static void searchMenu() {
		MemService memService = new MemService();
		MenuService menuService = new MenuService();
		BooService booService = new BooService();
		int sel = 0;

		while (sel != 6) {
			System.out.println("===================================================================================");
			System.out.println(
					"  1. 메뉴 고르기    |  2. 예약하기    |  3. 예약 확인    |  4. 선택지 추가하기    |  5. 로그아웃    |  6. 종료     ");
			System.out.println("===================================================================================");
			try {
				System.out.print("번호 입력>>> ");
				sel = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("다시 입력하여 주십시오.");
				continue;
			}
			switch (sel) {
			case 1:
				menuService.printMenuList(); // 음식 리스트 출력
				int num = 0;
				while (true) {
					try {
						System.out.print("[1]랜덤으로 선택하기 [2]음식 직접 선택하기 [3]돌아가기 >>> ");
						num = Integer.parseInt(scanner.nextLine());
						// [1]랜덤으로 선택하기
						if (num == 1) {
							String randomMeal = menuService.randomMeal();
							System.out.println("선택된 음식: " + randomMeal);
							while (num != 3) {
								System.out.print("[1]" + randomMeal + " 예약 [2]다시 선택하기 [3]취소 >>> ");
								num = Integer.parseInt(scanner.nextLine());
								// [1]예약하기
								if (num == 1) {
									inputReservation(randomMeal);
									break;
								}
								// [2]다시 선택하기
								else if (num == 2) {
									randomMeal = menuService.randomMeal();
									System.out.println("선택된 음식: " + randomMeal);
									continue;
								}
								// [3]취소
								else if (num == 3) {
									break;
								} else {
									System.out.println("다시 입력하여 주십시오.");
									continue;
								}
							}
							break;
						}
						// [2]음식 직접 선택하기
						else if (num == 2) {
							System.out.print("음식 입력 >>> ");
							String meal = scanner.nextLine();
							inputReservation(meal);
							break;
						}
						// [3]돌아가기
						else if (num == 3) {
							break;
						} else {
							System.out.println("다시 입력하여 주십시오.");
							continue;
						}
					} catch (NumberFormatException | NoSuchElementException e) {
						System.out.println("다시 입력하여 주십시오.");
						continue;
					}
				}
				break;
			case 2:
				inputReservation();
				booService = new BooService(); // 예약 정보 다시 로딩
				break;
			case 3:
				System.out.println("***********" + name + "님의 예약 내역***********");
				booService.checkBoo(id);
				System.out.println("********************************");
				break;
			case 4:
				addOption();
				menuService = new MenuService(); // 음식 정보 다시 로딩
				break;
			case 5:
				memService.logout(id);
				System.out.println(name + "님 이용해주셔서 감사합니다.");
				return;
			case 6:
				System.out.println(name + "님 이용해주셔서 감사합니다.");
				System.exit(0);
			default:
				System.out.println("다시 입력하여 주십시오.");
			}

		}

	}

	private static void addOption() {
		ResService resService = new ResService();
		MenuService menuService = new MenuService();
		ResMenuService resMenuService = new ResMenuService();
		int sel = 0;

		while (sel != 3) {
			System.out.println("===============================================");
			System.out.println("  1. 랜덤 음식 추가    |  2. 식당/메뉴 추가    |  3. 뒤로 가기     ");
			System.out.println("===============================================");
			try {
				System.out.print("번호 입력>>> ");
				sel = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("다시 입력하여 주십시오.");
				continue;
			}
			// 1. 랜덤 음식 추가
			if (sel == 1) {
				int foodCat = 0;

				System.out.print("추가할 음식 이름>>> ");
				String foodName = scanner.nextLine();
				while (true) {
					System.out.println("[1]한식, [2]일식, [3]중식, [4]양식, [5]기타");
					try {
						System.out.print("추가할 음식 종류 번호>>> ");
						foodCat = Integer.parseInt(scanner.nextLine());
						if (foodCat > 6 && foodCat < 1) {
							System.out.println("다시 입력하여 주십시오.");
							continue;
						} else {
							break;
						}
					} catch (NumberFormatException | NoSuchElementException e) {
						System.out.println("다시 입력하여 주십시오.");
						continue;
					}
				}
				if (menuService.addMenu(foodName, foodCat)) {
					System.out.println(foodName + " 등록되었습니다.");
				} else {
					System.out.println("이미 존재하는 음식입니다.");
				}
			}
			// 2. 식당/메뉴 추가
			else if (sel == 2) {
				int num = 0;

				while (num != 3) {
					try {
						System.out.println("[1]식당 추가\t[2]메뉴 추가\t[3]뒤로 가기");
						num = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException | NoSuchElementException e) {
						System.out.println("다시 입력하여 주십시오.");
						continue;
					}
					// [1]식당 추가
					if (num == 1) {
						System.out.print("추가할 식당 이름>>> ");
						String resName = scanner.nextLine();
						System.out.print("추가할 식당 번호>>> ");
						String pNumber = scanner.nextLine();
						System.out.print("추가할 식당 주소>>> ");
						String address = scanner.nextLine();
						if (resService.addRes(resName, pNumber, address)) {
							System.out.println(resName + " 식당이 등록되었습니다.");
							break;
						} else {
							System.out.println("이미 존재하는 식당입니다.");
						}
					}
					// [2]메뉴 추가
					else if (num == 2) {
						while (true) {
							System.out.print("추가할 식당 이름>>> ");
							String resName = scanner.nextLine();
							if (!resService.checkRes(resName)) {
								System.out.println("존재하지 않는 식당입니다. 다시 입력해주세요.");
								continue;
							} else {
								// 이름이 일치하는 식당 리스트 뽑아주기
								resService.printResList(resName);
								System.out.print("추가할 식당 번호>>> ");
								int res_id = Integer.parseInt(scanner.nextLine());
								menuService.printMenuList();
								System.out.print("추가할 메뉴 이름>>> ");
								String menu = scanner.nextLine();
								System.out.print("추가할 메뉴 가격>>> ");
								int cost = Integer.parseInt(scanner.nextLine());
								if (resMenuService.addResMenu(menu, res_id, cost)) {
									System.out.println(menu + " 메뉴 등록이 완료되었습니다.");
									break;
								} else {
									System.out.println("이미 존재하는 메뉴입니다.");
								}
							}
						}
						break;
					}
					// [3]뒤로 가기
					else if (num == 3) {
						break;
					} else {
						System.out.println("다시 입력하여 주십시오.");
					}
				}
			}
			// 3. 뒤로 가기
			else if (sel == 3) {
				return;
			} else {
				System.out.println("다시 입력하여 주십시오.");
			}
		}
	}

	private static void inputReservation() {
		ResService resService = new ResService();
		BooService booService = new BooService();

		System.out.println("*************식당 목록*************");
		resService.printResList();
		System.out.println("********************************");
		System.out.print("식당 번호 입력>>> ");
		int res = Integer.parseInt(scanner.nextLine());
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.print("예약하는 월(달) 입력>>> ");
		int month = Integer.parseInt(scanner.nextLine());
		System.out.print("예약하는 일(날짜) 입력>>> ");
		int day = Integer.parseInt(scanner.nextLine());
		System.out.print("예약하는 시간(시) 입력>>> ");
		int time = Integer.parseInt(scanner.nextLine());
		dateTime = LocalDateTime.of(dateTime.getYear(), month, day, time, 0);
		System.out.print("예약 인원 입력>>> ");
		int num = Integer.parseInt(scanner.nextLine());

		if (booService.makeBoo(res, id, dateTime, num)) {
			System.out.println("예약 완료");
		} else {
			System.out.println("이미 존재하는 예약입니다.");
		}
	}

	private static void inputReservation(String foodName) {
		ResMenuService resMenuService = new ResMenuService();
		BooService booService = new BooService();

		System.out.println("*************식당 목록*************");
		List<Restaurant> resList = resMenuService.printResList(foodName);
		if (resList.isEmpty()) {
			System.out.println("해당 음식이 있는 식당이 존재하지 않습니다.");
			System.out.println("********************************");
			return;
		}
		for (Restaurant restaurant : resList) {
			System.out.println(restaurant);
		}
		System.out.println("********************************");
		System.out.print("식당 번호 입력>>> ");
		int res = Integer.parseInt(scanner.nextLine());
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.print("예약하는 월(달) 입력>>> ");
		int month = Integer.parseInt(scanner.nextLine());
		System.out.print("예약하는 일(날짜) 입력>>> ");
		int day = Integer.parseInt(scanner.nextLine());
		System.out.print("예약하는 시간(시) 입력>>> ");
		int time = Integer.parseInt(scanner.nextLine());
		dateTime = LocalDateTime.of(dateTime.getYear(), month, day, time, 0);
		System.out.print("예약 인원 입력>>> ");
		int num = Integer.parseInt(scanner.nextLine());

		if (booService.makeBoo(res, id, dateTime, num)) {
			System.out.println("예약 완료");
		} else {
			System.out.println("이미 존재하는 예약입니다.");
		}
	}

	private static void inputLogin() {
		System.out.println("*****로그인*****");
		System.out.print("ID: ");
		id = scanner.nextLine();
		System.out.print("PW: ");
		pw = scanner.nextLine();
	}

	private static void inputRegister() {
		MemService memService = new MemService();

		System.out.println("*****회원가입*****");
		System.out.print("이름: ");
		name = scanner.nextLine();
		while (true) {
			System.out.print("ID: ");
			id = scanner.nextLine();
			if (memService.checkUser(id)) {
				System.out.println("이미 존재하는 아이디입니다.");
				continue;
			}
			break;
		}
		// 비밀번호 확인
		while (true) {
			System.out.print("PW: ");
			pw = scanner.nextLine();
			System.out.print("PW 재입력: ");
			String pw2 = scanner.nextLine();
			if (!pw.equals(pw2)) {
				System.out.println("입력한 Password가 일치하지 않습니다. 다시 입력해 주세요.");
				continue;
			}
			break;
		}
		System.out.println(name + "님 회원가입 성공!");
	}
}
