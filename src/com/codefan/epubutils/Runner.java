package com.codefan.epubutils;

public class Runner {

	public static void main(String[] args) {
		try {

			Reader reader = new Reader();
			reader.setMaxContentPerSection(1000);
			reader.setIsDissolvingStyleTag(true);

			Content epubContent = reader.getContent("C:\\eBooks/Alice in Wonderland.epub"); // IN THE YEAR 2889.epub

			reader.readSection(epubContent, 0);

			BookSection bookSection;

			// int k = 0;
			// for (int i = 0; i < 50; i++) {
			//
			// bookSection = epubContent.getBookSection(k);
			// System.out.println("\n" + k + "st Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());
			//
			// System.out.println("content: " + getHtmlBody(bookSection.getSectionContent()));
			//
			// k += getRandomBoolean() ? 1 : -1;
			//
			// if (k < 0)
			// k += 2;
			// }

			for (int i = 0; i < 50; i++) {
				bookSection = reader.readSection(epubContent, i);
				System.out.println("\n" + i + "st Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());

				System.out.println("content: " + bookSection.getSectionContent());
//				System.out.println("content: " + getHtmlBody(bookSection.getSectionContent()));
			}

			System.out.println("\n-------------------------------Going backwards!-------------------------------------\n");

			// Alice - 10. entry'de ba�a d�n�yor. trimStartPosition ve trimEndPosition 0 olarak al�n�yor.

			for (int i = 48; i >= 0; i--) {
				bookSection = reader.readSection(epubContent, i);
				System.out.println("\n" + i + "st Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());

//				System.out.println("content: " + getHtmlBody(bookSection.getSectionContent()));
				System.out.println("content: " + bookSection.getSectionContent());
			}

			// int x = 5;

			// bookSection = epubContent.getNextBookSection();
			// System.out.println("\n3rd Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());
			//
			// bookSection = epubContent.getPrevBookSection();
			// System.out.println("\n2nd Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());
			//
			// bookSection = epubContent.getBookSection(0);
			// System.out.println("\n1st Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());
			//
			// bookSection = epubContent.getBookSection(1);
			// System.out.println("\n2nd Book Section: \nlabel: " + bookSection.getLabel() + "; media-type: " + bookSection.getMediaType());

		} catch (ReadingException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

	private static String getHtmlBody(String htmlContent) throws ReadingException {
		int startOfBody = htmlContent.indexOf(Constants.TAG_BODY_START);
		int endOfBody = htmlContent.indexOf(Constants.TAG_BODY_END);

		if (startOfBody != -1 && endOfBody != -1) {
			return htmlContent.substring(startOfBody + Constants.TAG_BODY_START.length(), endOfBody);
		} else {
			throw new ReadingException("Exception while getting book section : Html body tags not found.");
		}
	}

	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}

}
