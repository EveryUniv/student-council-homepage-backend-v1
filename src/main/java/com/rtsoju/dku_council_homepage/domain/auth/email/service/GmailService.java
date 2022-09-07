package com.rtsoju.dku_council_homepage.domain.auth.email.service;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.email.dto.RequestEmailDto;
import com.rtsoju.dku_council_homepage.domain.user.service.UserService;
import com.rtsoju.dku_council_homepage.exception.ClassIdNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GmailService {
    private final Pattern classIdCheckPattern = Pattern.compile("^\\d{8}$");
    private final UserService userService;
    private final JavaMailSender javaMailSender;

    private final JwtProvider jwtProvider;

    @Value("${env_port}")
    private String port;

//    public void sendEmailForSignUp(RequestEmailDto dto) {
//        userService.verifyExistMemberWithClassId(dto.getClassId());
//        checkClassId(dto.getClassId());
//        SimpleMailMessage smm = new SimpleMailMessage();
//        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
//        smm.setTo(dto.getClassId() + "@dankook.ac.kr");
//        smm.setSubject("단국대학교 총학생회 이메일 인증");
//        String text = "http://www.dku54play.site:" + port + "/sign-up?token=" + emailToken + "&id=" + dto.getClassId();
//        smm.setText(text);
//        javaMailSender.send(smm);
//        return;
//    }
    public void sendEmailForSignUp(RequestEmailDto dto) throws MessagingException {
        userService.verifyExistMemberWithClassId(dto.getClassId());
        checkClassId(dto.getClassId());
        MimeMessage mailSenderMimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mailSenderMimeMessage, "UTF-8");
        mail.setFrom("단국대학교 총학생회 <54thplay@gmail.com>");
        mail.setTo(dto.getClassId()+"@dankook.ac.kr");
        mail.setSubject("단국대학교 총학생회 이메일 인증");
        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
        String text = "http://www.dku54play.site:" + port + "/sign-up?token=" + emailToken + "&id=" + dto.getClassId();
        mail.setText("<xlink\n" +
                "  href=\"https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700\"\n" +
                "  rel=\"stylesheet\"\n" +
                "  type=\"text/css\"\n" +
                ">\n" +
                "  <div style=\"background-color: #f9f9f9\">\n" +
                "    <div style=\"margin: 0px auto; max-width: 640px; background: transparent\">\n" +
                "      <table\n" +
                "        align=\"center\"\n" +
                "        cellpadding=\"0\"\n" +
                "        cellspacing=\"0\"\n" +
                "        style=\"font-size: 0px; width: 100%; background: transparent\"\n" +
                "        border=\"0\"\n" +
                "      >\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td\n" +
                "              style=\"\n" +
                "                text-align: center;\n" +
                "                vertical-align: top;\n" +
                "                direction: ltr;\n" +
                "                font-size: 0px;\n" +
                "                padding: 40px 0px;\n" +
                "              \"\n" +
                "            >\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  vertical-align: top;\n" +
                "                  display: inline-block;\n" +
                "                  direction: ltr;\n" +
                "                  font-size: 13px;\n" +
                "                  text-align: left;\n" +
                "                  width: 100%;\n" +
                "                \"\n" +
                "              >\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td\n" +
                "                        align=\"center\"\n" +
                "                        style=\"\n" +
                "                          word-break: break-word;\n" +
                "                          font-size: 0px;\n" +
                "                          padding: 0px;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <table\n" +
                "                          align=\"center\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          style=\"border-collapse: collapse; border-spacing: 0px\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <div\n" +
                "      style=\"\n" +
                "        max-width: 640px;\n" +
                "        margin: 0 auto;\n" +
                "        box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);\n" +
                "        border-radius: 4px;\n" +
                "        overflow: hidden;\n" +
                "      \"\n" +
                "    >\n" +
                "      <div style=\"margin: 0px auto; max-width: 640px; background: #ffffff\">\n" +
                "        <table\n" +
                "          align=\"center\"\n" +
                "          cellpadding=\"0\"\n" +
                "          cellspacing=\"0\"\n" +
                "          style=\"font-size: 0px; width: 100%; background: #ffffff\"\n" +
                "          border=\"0\"\n" +
                "        >\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td\n" +
                "                style=\"\n" +
                "                  text-align: center;\n" +
                "                  vertical-align: top;\n" +
                "                  direction: ltr;\n" +
                "                  font-size: 0px;\n" +
                "                  padding: 40px 50px;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    vertical-align: top;\n" +
                "                    display: inline-block;\n" +
                "                    direction: ltr;\n" +
                "                    font-size: 13px;\n" +
                "                    text-align: left;\n" +
                "                    width: 100%;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <table\n" +
                "                    cellpadding=\"0\"\n" +
                "                    cellspacing=\"0\"\n" +
                "                    width=\"100%\"\n" +
                "                    border=\"0\"\n" +
                "                  >\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"left\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #737f8d;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 16px;\n" +
                "                              line-height: 24px;\n" +
                "                              text-align: left;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            <h2\n" +
                "                              style=\"\n" +
                "                                font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                  Arial, Lucida Grande, sans-serif;\n" +
                "                                font-weight: 500;\n" +
                "                                font-size: 20px;\n" +
                "                                color: #4f545c;\n" +
                "                                letter-spacing: 0.27px;\n" +
                "                              \"\n" +
                "                            >\n" +
                "                              안녕하세요!\n" +
                "                            </h2>\n" +
                "                            <p>\n" +
                "                              단국대학교 재학생 인증을 위해, 아래의 링크를\n" +
                "                              클릭해 주세요. <br />\n" +
                "                              만약 내가 보낸 링크가 아니라면, 아래의 이메일로\n" +
                "                              문의 해 주세요.\n" +
                "                            </p>\n" +
                "                            <p>\n" +
                "                              <strong>학번:</strong>\n" +
                dto.getClassId() +
                "<br />\n" +
                "                            </p>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"center\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 10px 25px;\n" +
                "                            padding-top: 20px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <table\n" +
                "                            align=\"center\"\n" +
                "                            cellpadding=\"0\"\n" +
                "                            cellspacing=\"0\"\n" +
                "                            style=\"border-collapse: separate\"\n" +
                "                            border=\"0\"\n" +
                "                          >\n" +
                "                            <tbody>\n" +
                "                              <tr>\n" +
                "                                <td\n" +
                "                                  align=\"center\"\n" +
                "                                  valign=\"middle\"\n" +
                "                                  style=\"\n" +
                "                                    border: none;\n" +
                "                                    border-radius: 9999px;\n" +
                "                                    color: white;\n" +
                "                                    cursor: auto;\n" +
                "                                    padding: 15px 19px;\n" +
                "                                  \"\n" +
                "                                  bgcolor=\"#1C4991\"\n" +
                "                                >\n" +
                "                                  <a\n" +
                "                                    href=" +
                text +
                "                                    style=\"\n" +
                "                                      text-decoration: none;\n" +
                "                                      line-: 100%;\n" +
                "                                      background: #1c4991;\n" +
                "                                      color: white;\n" +
                "                                      font-family: ubuntu, helvetica, arial,\n" +
                "                                        sans-serif;\n" +
                "                                      font-size: 15px;\n" +
                "                                      font-weight: normal;\n" +
                "                                      text-transform: none;\n" +
                "                                      margin: 0px;\n" +
                "                                    \"\n" +
                "                                    target=\"_blank\"\n" +
                "                                    rel=\"noreferrer noopener\"\n" +
                "                                  >\n" +
                "                                    학번 인증하기\n" +
                "                                  </a>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </tbody>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 30px 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <p\n" +
                "                            style=\"\n" +
                "                              font-size: 1px;\n" +
                "                              margin: 0px auto;\n" +
                "                              border-top: 1px solid #dcddde;\n" +
                "                              width: 100%;\n" +
                "                            \"\n" +
                "                          ></p>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"left\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #747f8d;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 13px;\n" +
                "                              line-height: 16px;\n" +
                "                              text-align: left;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            <p>\n" +
                "                              도움이 필요하거나 피드백을 주고 싶나요?\n" +
                "                              <strong>ho991217@kakao.com</strong> 으로 문의\n" +
                "                              해주세요.<br />\n" +
                "                            </p>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </div>\n" +
                "                <!--[if mso | IE]>\n" +
                "        </td></tr></table>\n" +
                "        <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "      <!--[if mso | IE]>\n" +
                "        </div></td></tr></table>\n" +
                "        <![endif]-->\n" +
                "      <!--[if mso | IE]>\n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" style=\"width:640px;\">\n" +
                "          <tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "        <![endif]-->\n" +
                "      <div style=\"margin: 0px auto; max-width: 640px; background: transparent\">\n" +
                "        <table\n" +
                "          align=\"center\"\n" +
                "          cellpadding=\"0\"\n" +
                "          cellspacing=\"0\"\n" +
                "          style=\"font-size: 0px; width: 100%; background: transparent\"\n" +
                "          border=\"0\"\n" +
                "        >\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td\n" +
                "                style=\"\n" +
                "                  text-align: center;\n" +
                "                  vertical-align: top;\n" +
                "                  direction: ltr;\n" +
                "                  font-size: 0px;\n" +
                "                  padding: 20px 0px;\n" +
                "                \"\n" +
                "              >\n" +
                "                <!--[if mso | IE]>\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n" +
                "        <![endif]-->\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    vertical-align: top;\n" +
                "                    display: inline-block;\n" +
                "                    direction: ltr;\n" +
                "                    font-size: 13px;\n" +
                "                    text-align: left;\n" +
                "                    width: 100%;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <table\n" +
                "                    cellpadding=\"0\"\n" +
                "                    cellspacing=\"0\"\n" +
                "                    width=\"100%\"\n" +
                "                    border=\"0\"\n" +
                "                  >\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"center\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #99aab5;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 12px;\n" +
                "                              line-height: 24px;\n" +
                "                              text-align: center;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            • 단국대학교 총학생회에서 보냄 •\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"center\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #99aab5;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 12px;\n" +
                "                              line-height: 24px;\n" +
                "                              text-align: center;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            경기도 용인시 수지구 죽전동 1491 단국대학교 혜당관\n" +
                "                            406호 총학생회실\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"left\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #000000;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 13px;\n" +
                "                              line-height: 22px;\n" +
                "                              text-align: left;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </div>\n" +
                "                <!--[if mso | IE]>\n" +
                "        </td></tr></table>\n" +
                "        <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "      <!--[if mso | IE]>\n" +
                "        </td></tr></table></div>\n" +
                "        <![endif]-->\n" +
                "    </div></div\n" +
                "></xlink>\n",true);
        javaMailSender.send(mailSenderMimeMessage);
        return;
    }

//    public void sendEmailForChangePW(RequestEmailDto dto){
//        userService.verifyExistMemberWithClassId(dto.getClassId());
//        checkClassId(dto.getClassId());
//        SimpleMailMessage smm = new SimpleMailMessage();
//        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
//        smm.setTo(dto.getClassId()+"@dankook.ac.kr");
//        smm.setSubject("단국대학교 총학생회 비밀번호 변경");
//        smm.setText("http://www.dku54play.site:" + port + "/password?token=" + emailToken + "&id=" + dto.getClassId());
//        javaMailSender.send(smm);
//        return;
//    }

    public void sendEmailForChangePW(RequestEmailDto dto) throws MessagingException {
        userService.verifyExistMemberWithClassId(dto.getClassId());
        checkClassId(dto.getClassId());
        MimeMessage mailSenderMimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mailSenderMimeMessage, "UTF-8");
        mail.setFrom("단국대학교 총학생회 <54thplay@gmail.com>");
        mail.setTo(dto.getClassId()+"@dankook.ac.kr");
        mail.setSubject("단국대학교 총학생회 이메일 인증");
        String emailToken = jwtProvider.createEmailValidationToken(dto.getClassId());
        String text = "http://www.dku54play.site:" + port + "/password?token=" + emailToken + "&id=" + dto.getClassId();
        mail.setText("<xlink\n" +
                "  href=\"https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700\"\n" +
                "  rel=\"stylesheet\"\n" +
                "  type=\"text/css\"\n" +
                ">\n" +
                "  <div style=\"background-color: #f9f9f9\">\n" +
                "    <div style=\"margin: 0px auto; max-width: 640px; background: transparent\">\n" +
                "      <table\n" +
                "        align=\"center\"\n" +
                "        cellpadding=\"0\"\n" +
                "        cellspacing=\"0\"\n" +
                "        style=\"font-size: 0px; width: 100%; background: transparent\"\n" +
                "        border=\"0\"\n" +
                "      >\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td\n" +
                "              style=\"\n" +
                "                text-align: center;\n" +
                "                vertical-align: top;\n" +
                "                direction: ltr;\n" +
                "                font-size: 0px;\n" +
                "                padding: 40px 0px;\n" +
                "              \"\n" +
                "            >\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  vertical-align: top;\n" +
                "                  display: inline-block;\n" +
                "                  direction: ltr;\n" +
                "                  font-size: 13px;\n" +
                "                  text-align: left;\n" +
                "                  width: 100%;\n" +
                "                \"\n" +
                "              >\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                  <tbody>\n" +
                "                    <tr>\n" +
                "                      <td\n" +
                "                        align=\"center\"\n" +
                "                        style=\"\n" +
                "                          word-break: break-word;\n" +
                "                          font-size: 0px;\n" +
                "                          padding: 0px;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <table\n" +
                "                          align=\"center\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          style=\"border-collapse: collapse; border-spacing: 0px\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </tbody>\n" +
                "                </table>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <div\n" +
                "      style=\"\n" +
                "        max-width: 640px;\n" +
                "        margin: 0 auto;\n" +
                "        box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);\n" +
                "        border-radius: 4px;\n" +
                "        overflow: hidden;\n" +
                "      \"\n" +
                "    >\n" +
                "      <div style=\"margin: 0px auto; max-width: 640px; background: #ffffff\">\n" +
                "        <table\n" +
                "          align=\"center\"\n" +
                "          cellpadding=\"0\"\n" +
                "          cellspacing=\"0\"\n" +
                "          style=\"font-size: 0px; width: 100%; background: #ffffff\"\n" +
                "          border=\"0\"\n" +
                "        >\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td\n" +
                "                style=\"\n" +
                "                  text-align: center;\n" +
                "                  vertical-align: top;\n" +
                "                  direction: ltr;\n" +
                "                  font-size: 0px;\n" +
                "                  padding: 40px 50px;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    vertical-align: top;\n" +
                "                    display: inline-block;\n" +
                "                    direction: ltr;\n" +
                "                    font-size: 13px;\n" +
                "                    text-align: left;\n" +
                "                    width: 100%;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <table\n" +
                "                    cellpadding=\"0\"\n" +
                "                    cellspacing=\"0\"\n" +
                "                    width=\"100%\"\n" +
                "                    border=\"0\"\n" +
                "                  >\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"left\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #737f8d;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 16px;\n" +
                "                              line-height: 24px;\n" +
                "                              text-align: left;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            <h2\n" +
                "                              style=\"\n" +
                "                                font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                  Arial, Lucida Grande, sans-serif;\n" +
                "                                font-weight: 500;\n" +
                "                                font-size: 20px;\n" +
                "                                color: #4f545c;\n" +
                "                                letter-spacing: 0.27px;\n" +
                "                              \"\n" +
                "                            >\n" +
                "                              안녕하세요!\n" +
                "                            </h2>\n" +
                "                            <p>\n" +
                "                              단국대학교 비밀번호 변경 인증을 위해, 아래의 링크를\n" +
                "                              클릭해 주세요. <br />\n" +
                "                              만약 내가 보낸 링크가 아니라면, 아래의 이메일로\n" +
                "                              문의 해 주세요.\n" +
                "                            </p>\n" +
                "                            <p>\n" +
                "                              <strong>학번:</strong>\n" +
                dto.getClassId() +
                "<br />\n" +
                "                            </p>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"center\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 10px 25px;\n" +
                "                            padding-top: 20px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <table\n" +
                "                            align=\"center\"\n" +
                "                            cellpadding=\"0\"\n" +
                "                            cellspacing=\"0\"\n" +
                "                            style=\"border-collapse: separate\"\n" +
                "                            border=\"0\"\n" +
                "                          >\n" +
                "                            <tbody>\n" +
                "                              <tr>\n" +
                "                                <td\n" +
                "                                  align=\"center\"\n" +
                "                                  valign=\"middle\"\n" +
                "                                  style=\"\n" +
                "                                    border: none;\n" +
                "                                    border-radius: 9999px;\n" +
                "                                    color: white;\n" +
                "                                    cursor: auto;\n" +
                "                                    padding: 15px 19px;\n" +
                "                                  \"\n" +
                "                                  bgcolor=\"#1C4991\"\n" +
                "                                >\n" +
                "                                  <a\n" +
                "                                    href=" +
                text +
                "                                    style=\"\n" +
                "                                      text-decoration: none;\n" +
                "                                      line-: 100%;\n" +
                "                                      background: #1c4991;\n" +
                "                                      color: white;\n" +
                "                                      font-family: ubuntu, helvetica, arial,\n" +
                "                                        sans-serif;\n" +
                "                                      font-size: 15px;\n" +
                "                                      font-weight: normal;\n" +
                "                                      text-transform: none;\n" +
                "                                      margin: 0px;\n" +
                "                                    \"\n" +
                "                                    target=\"_blank\"\n" +
                "                                    rel=\"noreferrer noopener\"\n" +
                "                                  >\n" +
                "                                    비밀번호 변경\n" +
                "                                  </a>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </tbody>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 30px 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <p\n" +
                "                            style=\"\n" +
                "                              font-size: 1px;\n" +
                "                              margin: 0px auto;\n" +
                "                              border-top: 1px solid #dcddde;\n" +
                "                              width: 100%;\n" +
                "                            \"\n" +
                "                          ></p>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"left\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #747f8d;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 13px;\n" +
                "                              line-height: 16px;\n" +
                "                              text-align: left;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            <p>\n" +
                "                              도움이 필요하거나 피드백을 주고 싶나요?\n" +
                "                              <strong>ho991217@kakao.com</strong> 으로 문의\n" +
                "                              해주세요.<br />\n" +
                "                            </p>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </div>\n" +
                "                <!--[if mso | IE]>\n" +
                "        </td></tr></table>\n" +
                "        <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "      <!--[if mso | IE]>\n" +
                "        </div></td></tr></table>\n" +
                "        <![endif]-->\n" +
                "      <!--[if mso | IE]>\n" +
                "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"640\" style=\"width:640px;\">\n" +
                "          <tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                "        <![endif]-->\n" +
                "      <div style=\"margin: 0px auto; max-width: 640px; background: transparent\">\n" +
                "        <table\n" +
                "          align=\"center\"\n" +
                "          cellpadding=\"0\"\n" +
                "          cellspacing=\"0\"\n" +
                "          style=\"font-size: 0px; width: 100%; background: transparent\"\n" +
                "          border=\"0\"\n" +
                "        >\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td\n" +
                "                style=\"\n" +
                "                  text-align: center;\n" +
                "                  vertical-align: top;\n" +
                "                  direction: ltr;\n" +
                "                  font-size: 0px;\n" +
                "                  padding: 20px 0px;\n" +
                "                \"\n" +
                "              >\n" +
                "                <!--[if mso | IE]>\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:640px;\">\n" +
                "        <![endif]-->\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    vertical-align: top;\n" +
                "                    display: inline-block;\n" +
                "                    direction: ltr;\n" +
                "                    font-size: 13px;\n" +
                "                    text-align: left;\n" +
                "                    width: 100%;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <table\n" +
                "                    cellpadding=\"0\"\n" +
                "                    cellspacing=\"0\"\n" +
                "                    width=\"100%\"\n" +
                "                    border=\"0\"\n" +
                "                  >\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"center\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #99aab5;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 12px;\n" +
                "                              line-height: 24px;\n" +
                "                              text-align: center;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            • 단국대학교 총학생회에서 보냄 •\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"center\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #99aab5;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 12px;\n" +
                "                              line-height: 24px;\n" +
                "                              text-align: center;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                            경기도 용인시 수지구 죽전동 1491 단국대학교 혜당관\n" +
                "                            406호 총학생회실\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td\n" +
                "                          align=\"left\"\n" +
                "                          style=\"\n" +
                "                            word-break: break-word;\n" +
                "                            font-size: 0px;\n" +
                "                            padding: 0px;\n" +
                "                          \"\n" +
                "                        >\n" +
                "                          <div\n" +
                "                            style=\"\n" +
                "                              cursor: auto;\n" +
                "                              color: #000000;\n" +
                "                              font-family: Whitney, Helvetica Neue, Helvetica,\n" +
                "                                Arial, Lucida Grande, sans-serif;\n" +
                "                              font-size: 13px;\n" +
                "                              line-height: 22px;\n" +
                "                              text-align: left;\n" +
                "                            \"\n" +
                "                          >\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </div>\n" +
                "                <!--[if mso | IE]>\n" +
                "        </td></tr></table>\n" +
                "        <![endif]-->\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "      <!--[if mso | IE]>\n" +
                "        </td></tr></table></div>\n" +
                "        <![endif]-->\n" +
                "    </div></div\n" +
                "></xlink>\n",true);
        javaMailSender.send(mailSenderMimeMessage);
        return;
    }



    private void checkClassId(String classId){
        if(!classIdCheckPattern.matcher(classId).matches()){
            throw new ClassIdNotMatchException("잘못된 형식의 학번입니다.");
        }
    }
}
