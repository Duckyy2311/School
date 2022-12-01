/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoMemberEvaluation;
import java.sql.SQLException;
import model.MemberEvaluation;
import view_inputs.Inputter;

/**
 *
 * @author Dinh Quoc Tung
 */
public class MemberEvaluationController {

    public static void showPagination(int id, String team_id) throws SQLException, ClassNotFoundException {
        int count = DaoMemberEvaluation.countMemberEvaluationTrainer("a.member_eval_id", "", id, team_id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMemberEvaluation.memberEvaluationDisplayByPage(1, "a.member_eval_id", "", id, team_id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMemberEvaluation.memberEvaluationDisplayByPage(1, "a.member_eval_id", "", id, team_id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Member Evalution List");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, "a.member_eval_id", "", id, team_id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, "a.team_eval_id", "", id, team_id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }

        }
    }

    public static void showPaginationStudent(int id, String team_id) throws SQLException, ClassNotFoundException {
        int count = DaoMemberEvaluation.countStudent("a.member_eval_id", "", id, team_id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(1, "a.member_eval_id", "", id, team_id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, "a.member_eval_id", "", id, team_id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Member Evalution List");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, "a.team_eval_id", "", id, team_id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, "a.team_eval_id", "", id, team_id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }

        }
    }

    public void memberEvaluationUpdate(int id, String team_id) throws SQLException, ClassNotFoundException {
        DaoMemberEvaluation.memberEvaluationDisplayByPage(1, "a.member_eval_id", "", id, team_id);
        int member_evaluation = Inputter.inputInt("Enter Member Evaluation id to update: ", 0, Integer.MAX_VALUE);
        MemberEvaluation c1 = DaoMemberEvaluation.checkMember(member_evaluation);
        while (c1 == null) {
            member_evaluation = Inputter.inputInt("This member eval isn't exist,re-enter: ", 0, Integer.MAX_VALUE);
            c1 = DaoMemberEvaluation.checkMember(member_evaluation);

        }

        System.out.printf("%-15s%-20s%-20s%-15s%-20s\n",
                "Member Evaluation ",
                "evaluation",
                "criteria",
                "grade",
                "converted loc",
                "note ");

        DaoMemberEvaluation.memberEvaluationDisplayByPage(1, "a.member_eval_id", String.valueOf(member_evaluation), id, team_id);
        double grade = 0;
        int op = Inputter.inputInt("Old grade: [" + c1.getGrade() + "], Do u want to change grade(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            grade = Inputter.inputDouble("Enter new grade:", 0, 10);
        }
        if (op == 2) {
            grade = c1.getGrade();
        }

        int converted_loc;
        converted_loc = c1.getConverted_loc();

        String note = null;
        op = Inputter.inputInt("Old note: [" + c1.getNote() + "] Do u want to change note(1-Y,2-N): ", 1, 2);
        if (op == 1) {
            note = Inputter.inputNonBlankStr("input new  note:");
        }
        if (op == 2) {
            note = c1.getNote();
        }

        MemberEvaluation c2 = new MemberEvaluation(c1.getEvaluation_id(), c1.getCriteria_id(), c1.getConverted_loc(), grade, note);
        c2.setMember_eval_id(c1.getMember_eval_id());
        DaoMemberEvaluation.updateMemberEvaluation(c2);

    }

    public void searchFilter(int id, String team_id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Evaluation ID      2.Criteria name");
            System.out.println("3. Back to Menu ");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1:
                    searchInt("Enter Evaluation ID: ", "a.evaluation_id", 1, Integer.MAX_VALUE, id, team_id);
                    break;
                case 2:
                    searchString("Enter a part of Criteria name: ", "b.criteria_name", id, team_id);
                    break;

                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchInt(String msg, String field, int min, int max, int id, String team_id) throws SQLException, ClassNotFoundException {
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoMemberEvaluation.countMemberEvaluationTrainer(field, String.valueOf(filter), id, team_id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMemberEvaluation.memberEvaluationDisplayByPage(1, field, String.valueOf(filter), id, team_id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, field, String.valueOf(filter), id, team_id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Member Eval List");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchString(String msg, String field, int id, String team_id) throws SQLException, ClassNotFoundException {
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoMemberEvaluation.countMemberEvaluationTrainer(field, String.valueOf(filter), id, team_id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMemberEvaluation.memberEvaluationDisplayByPage(1, field, filter, id, team_id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, field, String.valueOf(filter), id, team_id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Member Eval List");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPage(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchFilterStudent(int id, String team_id) throws SQLException, ClassNotFoundException {
        int option;
        while (true) {
            System.out.println("\nFilter by--------- ");
            System.out.println("1. Evaluation ID      2.Criteria name");
            System.out.println("3. Back to Menu ");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1:
                    searchIntStudent("Enter Evaluation ID: ", "a.evaluation_id", 1, Integer.MAX_VALUE, id, team_id);
                    break;
                case 2:
                    searchStringStudent("Enter a part of Criteria name: ", "b.criteria_name", id, team_id);
                    break;

                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchIntStudent(String msg, String field, int min, int max, int id, String team_id) throws SQLException, ClassNotFoundException {
        int filter = Inputter.inputInt(msg, min, max);

        int count = DaoMemberEvaluation.countStudent(field, String.valueOf(filter), id, team_id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(1, field, String.valueOf(filter), id, team_id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter), id, team_id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Member Eval List");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }

    public void searchStringStudent(String msg, String field, int id, String team_id) throws SQLException, ClassNotFoundException {
        String filter = Inputter.inputNonBlankStr(msg);

        int count = DaoMemberEvaluation.countStudent(field, String.valueOf(filter), id, team_id);
        int countPage = count / 5;
        if (count % 5 != 0) {
            countPage++;
        }
        int noPage;
        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(1, field, filter, id, team_id);
        noPage = Inputter.inputInt("Enter no.page you want to show 'from 1 to " + countPage + "'(0-exit): ", 0, countPage);
        if (noPage == 0) {
            return;
        } else {
            DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter), id, team_id);
        }
        int option;
        while (true) {
            if (noPage == 0) {
                return;
            }
            System.out.println("\nShow--------- ");
            System.out.println("1. Next page");
            System.out.println("2. Pre page");
            System.out.println("3. Back to Member Eval List");

            System.out.print("Your option: ");

            option = Inputter.inputInt("", 1, 3);
            switch (option) {
                case 1: {
                    noPage++;
                    if (noPage > countPage) {
                        System.out.println("There is no more page!");
                        noPage--;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                case 2: {
                    noPage--;
                    if (noPage <= 0) {
                        System.out.println("There is no more page!");
                        noPage++;
                        break;
                    } else {
                        DaoMemberEvaluation.memberEvaluationDisplayByPageStudent(noPage, field, String.valueOf(filter), id, team_id);
                        break;
                    }
                }
                default:
                    break;
            }
            if (option == 3) {
                return;
            }
        }
    }
}
