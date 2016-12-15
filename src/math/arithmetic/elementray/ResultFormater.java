package math.arithmetic.elementray;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

public class ResultFormater implements Closeable {
	// COUNT_PER_LINE = 5; COLUMN_WIDTH = 14 for single operator
	// COUNT_PER_LINE = 4; COLUMN_WIDTH = 18 for double operator
	static final int COUNT_PER_LINE = 4;
	static final int LINE_PER_BLOCK = 10;
	static final int LINE_PER_PAGE = 20;
	static final int COLUMN_WIDTH = 18;
	// 以上变量配合WORD模板使用

	int indexCurLine = 0;
	int lineCurPage = 0;
	int prevSpaceCount = 0;
	StringBuilder sb = new StringBuilder();
	Writer writer;

	public ResultFormater(Writer writer) {
		this.writer = writer;
	}

	public void format(String content) throws IOException {
		sb.append(content);
		writer.write(sb.toString());
		prevSpaceCount = COLUMN_WIDTH - (sb.length() - prevSpaceCount);
		sb.setLength(0);
		for (int i = 0; i < prevSpaceCount; i++) {
			sb.append(' ');
		}
		indexCurLine++;
		if (indexCurLine >= COUNT_PER_LINE) {
			writer.write("\r\n");
			indexCurLine = 0;
			sb.setLength(0);
			prevSpaceCount = 0;
			lineCurPage++;
			if (lineCurPage == LINE_PER_BLOCK) {
				writer.write("\r\n\r\n\r\n");
			}
			if (lineCurPage >= LINE_PER_PAGE) {
				lineCurPage = 0;
				writer.write("\r\n");
			}
		}
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}
}